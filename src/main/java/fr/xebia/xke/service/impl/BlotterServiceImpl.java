package fr.xebia.xke.service.impl;

import com.google.common.collect.Lists;
import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.ProductRepository;
import fr.xebia.xke.service.BlotterService;
import org.springframework.stereotype.Service;

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
        List<ProductShortInfo> productShortInfos = productRepository.findAllProductInfosBy(user);

        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        for (ProductShortInfo productShortInfo : productShortInfos) {
            if (tasks.containsKey(productShortInfo.getId())) {
                productShortInfo.setTasks(Lists.newArrayList(tasks.get(productShortInfo.getId())));
            }
        }

        return productShortInfos;
    }

    @Override
    public List<ProductShortInfo> findProductsForUserBlotter(User user) {
        List<ProductShortInfo> productShortInfos = productRepository.findUserProductInfosBy(user);

        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        List<ProductShortInfo> productShortInfosWithTasks = Lists.newArrayList();

        for (ProductShortInfo productShortInfo : productShortInfos) {
            if (tasks.containsKey(productShortInfo.getId())) {
                productShortInfo.setTasks(Lists.newArrayList(tasks.get(productShortInfo.getId())));

                productShortInfosWithTasks.add(productShortInfo);
            }
        }

        return productShortInfosWithTasks;
    }

    @Override
    public List<ProductShortInfo> findProductsForTeamBlotter(User user) {
        List<ProductShortInfo> productShortInfos = productRepository.findTeamProductInfosBy(user);

        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        List<ProductShortInfo> productShortInfosWithTasks = Lists.newArrayList();

        for (ProductShortInfo productShortInfo : productShortInfos) {
            if (tasks.containsKey(productShortInfo.getId())) {
                productShortInfo.setTasks(Lists.newArrayList(tasks.get(productShortInfo.getId())));

                productShortInfosWithTasks.add(productShortInfo);
            }
        }

        return productShortInfosWithTasks;
    }

}
