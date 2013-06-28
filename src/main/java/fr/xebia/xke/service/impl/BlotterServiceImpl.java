package fr.xebia.xke.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.Product;
import fr.xebia.xke.model.Task;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.ProductRepository;
import fr.xebia.xke.service.BlotterService;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class BlotterServiceImpl implements BlotterService {

    private ProductRepository productRepository;

    private TaskHelper taskHelper;

    @Inject
    public BlotterServiceImpl(ProductRepository productRepository, TaskHelper taskHelper) {
        this.productRepository = productRepository;
        this.taskHelper = taskHelper;
    }

    @Override
    public List<ProductShortInfo> findProductsForAllProductsBlotter(User user) {
        return Lists.newArrayList(FluentIterable.from(ALL_PRODUCTS())
                .filter(BY_PARTICIPANTS_WITH(user))
                .transform(TO_SHORT_INFO)
                .transform(WITH_TASKS(FOR_USER_AND_TEAM_MEMBERS_OF(user)))
                .toImmutableList());
    }

    @Override
    public List<ProductShortInfo> findProductsForTeamBlotter(User user) {
        return Lists.newArrayList(FluentIterable.from(ALL_PRODUCTS())
                .filter(BY_PARTICIPANTS_WITH(user))
                .transform(TO_SHORT_INFO)
                .transform(WITH_TASKS(FOR_USER_AND_TEAM_MEMBERS_OF(user)))
                .filter(ONLY_WITH_TASKS)
                .toImmutableList());
    }

    @Override
    public List<ProductShortInfo> findProductsForUserBlotter(User user) {
        return Lists.newArrayList(FluentIterable.from(ALL_PRODUCTS())
                .filter(BY_PARTICIPANTS_WITH(user))
                .transform(TO_SHORT_INFO)
                .transform(WITH_TASKS(FOR_USER_AND_USER_TEAMS_OF(user)))
                .filter(ONLY_WITH_TASKS)
                .toImmutableList());
    }

    private List<Product> ALL_PRODUCTS() {
        return productRepository.findAll();
    }

    private Map<Long, Collection<TaskShortInfo>> FOR_USER_AND_TEAM_MEMBERS_OF(User user) {
        return taskHelper.findAllPendingTasksForUserAndTeamMembers(user);
    }

    private Map<Long, Collection<TaskShortInfo>> FOR_USER_AND_USER_TEAMS_OF(User user) {
        return taskHelper.findAllPendingTasksForUserAndUserTeams(user);
    }

    private static Predicate<Product> BY_PARTICIPANTS_WITH(User user) {
        return new ByParticipants(user);
    }

    private static Function<ProductShortInfo, ProductShortInfo> WITH_TASKS(final Map<Long, Collection<TaskShortInfo>> tasksByProductId) {
        return new Function<ProductShortInfo, ProductShortInfo>() {

            @Nullable
            @Override
            public ProductShortInfo apply(ProductShortInfo productShortInfo) {
                Collection<TaskShortInfo> tasks = tasksByProductId.get(productShortInfo.getId());

                if (tasks != null) {
                    productShortInfo.setTasks(Lists.newArrayList(tasks));
                }

                return productShortInfo;
            }

        };
    }

    private static final Function<Product, ProductShortInfo> TO_SHORT_INFO = new Function<Product, ProductShortInfo>() {

        @Nullable
        @Override
        public ProductShortInfo apply(Product product) {
            return new ProductShortInfo(product);
        }

    };

    private static final Predicate<ProductShortInfo> ONLY_WITH_TASKS = new Predicate<ProductShortInfo>() {

        @Override
        public boolean apply(ProductShortInfo productShortInfo) {
            return !productShortInfo.getTasks().isEmpty();
        }

    };

}
