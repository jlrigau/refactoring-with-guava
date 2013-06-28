package fr.xebia.xke.service;

import fr.xebia.xke.AbstractTest;
import fr.xebia.xke.dto.ProductShortInfo;
import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.UserRepository;
import fr.xebia.xke.service.impl.TaskHelper;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class BlotterServiceTest extends AbstractTest {

    @Inject
    private BlotterService blotterService;

    @Inject
    private UserRepository userRepository;

    @Inject
    private TaskHelper taskHelper;

    @Test
    public void should_find_products_for_all_products_blotter_for_user_sales_europe() {
        verifyProductsInAllProductsBlotterFor("user.sales.europe", "product_1", "product_2", "product_3", "product_4", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_sales_paris() {
        verifyProductsInAllProductsBlotterFor("user.sales.paris", "product_2", "product_3", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_sales_engineer_europe() {
        verifyProductsInAllProductsBlotterFor("user.sales_engineer.europe", "product_1", "product_2", "product_3", "product_4", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_eng_1_europe() {
        verifyProductsInAllProductsBlotterFor("user.eng_1.europe", "product_1", "product_2", "product_3", "product_4", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_eng_2_europe() {
        verifyProductsInAllProductsBlotterFor("user.eng_2.europe", "product_1", "product_2", "product_3", "product_4", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_trader_1_europe() {
        verifyProductsInAllProductsBlotterFor("user.trader_1.europe", "product_1", "product_3", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_trader_2_europe() {
        verifyProductsInAllProductsBlotterFor("user.trader_2.europe", "product_1", "product_3", "product_5", "product_6", "product_7");
    }

    @Test
    public void should_find_products_for_all_products_blotter_for_user_trader_asia() {
        verifyProductsInAllProductsBlotterFor("user.trader.asia", "product_2", "product_4", "product_7");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_sales_europe() {
        verifyProductsInTeamBlotterFor("user.sales.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_sales_paris() {
        verifyProductsInTeamBlotterFor("user.sales.paris", "product_2");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_sales_engineer_europe() {
        verifyProductsInTeamBlotterFor("user.sales_engineer.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_eng_1_europe() {
        verifyProductsInTeamBlotterFor("user.eng_1.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_eng_2_europe() {
        verifyProductsInTeamBlotterFor("user.eng_2.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_trader_1_europe() {
        verifyProductsInTeamBlotterFor("user.trader_1.europe", "product_1");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_trader_2_europe() {
        verifyProductsInTeamBlotterFor("user.trader_2.europe", "product_1");
    }

    @Test
    public void should_find_products_for_team_blotter_for_user_trader_asia() {
        verifyProductsInTeamBlotterFor("user.trader.asia", "product_2");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_sales_europe() {
        verifyProductsInUserBlotterFor("user.sales.europe", "product_1");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_sales_paris() {
        verifyProductsInUserBlotterFor("user.sales.paris", "product_2");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_sales_engineer_europe() {
        verifyProductsInUserBlotterFor("user.sales_engineer.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_eng_1_europe() {
        verifyProductsInUserBlotterFor("user.eng_1.europe", "product_2");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_eng_2_europe() {
        verifyProductsInUserBlotterFor("user.eng_2.europe", "product_1", "product_2");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_trader_1_europe() {
        verifyProductsInUserBlotterFor("user.trader_1.europe");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_trader_2_europe() {
        verifyProductsInUserBlotterFor("user.trader_2.europe", "product_1");
    }

    @Test
    public void should_find_products_for_user_blotter_for_user_trader_asia() {
        verifyProductsInUserBlotterFor("user.trader.asia", "product_2");
    }

    private void verifyProductsInAllProductsBlotterFor(String userName, String... productNames) {
        // Given
        User user = userRepository.findByName(userName);

        // When
        List<ProductShortInfo> productShortInfos = blotterService.findProductsForAllProductsBlotter(user);

        // Then
        if (productNames.length == 0) {
            assertThat(productShortInfos).isEmpty();
        } else {
            assertThat(extractProperty("name").from(productShortInfos)).containsOnly(productNames);

            Map<Long, Collection<TaskShortInfo>> tasksForUserAndTeamMembers = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

            for (ProductShortInfo productShortInfo : productShortInfos) {
                List<TaskShortInfo> tasks = productShortInfo.getTasks();

                if (!tasks.isEmpty()) {
                    assertThat(tasks).containsAll(tasksForUserAndTeamMembers.get(productShortInfo.getId()));
                } else {
                    assertThat(tasksForUserAndTeamMembers.get(productShortInfo.getId())).isNull();
                }
            }
        }
    }

    private void verifyProductsInTeamBlotterFor(String userName, String... productNames) {
        // Given
        User user = userRepository.findByName(userName);

        // When
        List<ProductShortInfo> productShortInfos = blotterService.findProductsForTeamBlotter(user);

        // Then
        if (productNames.length == 0) {
            assertThat(productShortInfos).isEmpty();
        } else {
            assertThat(extractProperty("name").from(productShortInfos)).containsOnly(productNames);

            Map<Long, Collection<TaskShortInfo>> tasksForUserAndTeamMembers = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

            for (ProductShortInfo productShortInfo : productShortInfos) {
                List<TaskShortInfo> tasks = productShortInfo.getTasks();

                assertThat(tasks).isNotEmpty();
                assertThat(tasks).containsAll(tasksForUserAndTeamMembers.get(productShortInfo.getId()));
            }
        }
    }

    private void verifyProductsInUserBlotterFor(String userName, String... productNames) {
        // Given
        User user = userRepository.findByName(userName);

        // When
        List<ProductShortInfo> productShortInfos = blotterService.findProductsForUserBlotter(user);

        // Then
        if (productNames.length == 0) {
            assertThat(productShortInfos).isEmpty();
        } else {
            assertThat(extractProperty("name").from(productShortInfos)).containsOnly(productNames);

            Map<Long, Collection<TaskShortInfo>> tasksForUserAndUserTeams = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

            for (ProductShortInfo productShortInfo : productShortInfos) {
                List<TaskShortInfo> tasks = productShortInfo.getTasks();

                assertThat(tasks).isNotEmpty();
                assertThat(tasks).containsAll(tasksForUserAndUserTeams.get(productShortInfo.getId()));
            }
        }
    }

}
