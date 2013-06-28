package fr.xebia.xke.service;

import fr.xebia.xke.AbstractTest;
import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.UserRepository;
import fr.xebia.xke.service.impl.TaskHelper;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

public class TaskHelperTest extends AbstractTest {

    @Inject
    private TaskHelper taskHelper;

    @Inject
    private UserRepository userRepository;

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_sales_europe() {
        // Given
        User user = userRepository.findByName("user.sales.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("sales_user_task", "sales_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_sales_paris() {
        // Given
        User user = userRepository.findByName("user.sales.paris");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("sales_user_task", "sales_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_sales_engineer_europe() {
        // Given
        User user = userRepository.findByName("user.sales_engineer.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("sales_team_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_eng_1_europe() {
        // Given
        User user = userRepository.findByName("user.eng_1.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_eng_2_europe() {
        // Given
        User user = userRepository.findByName("user.eng_2.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("engineer_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_trader_1_europe() {
        // Given
        User user = userRepository.findByName("user.trader_1.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_trader_2_europe() {
        // Given
        User user = userRepository.findByName("user.trader_2.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("trader_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_user_teams_for_user_trader_asia() {
        // Given
        User user = userRepository.findByName("user.trader.asia");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndUserTeams(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_user_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_sales_europe() {
        // Given
        User user = userRepository.findByName("user.sales.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("sales_user_task", "sales_team_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("sales_user_task", "sales_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_sales_paris() {
        // Given
        User user = userRepository.findByName("user.sales.paris");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("sales_user_task", "sales_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_sales_engineer_europe() {
        // Given
        User user = userRepository.findByName("user.sales_engineer.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("engineer_user_task", "sales_user_task", "sales_team_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task", "sales_user_task", "sales_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_eng_1_europe() {
        // Given
        User user = userRepository.findByName("user.eng_1.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("engineer_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_eng_2_europe() {
        // Given
        User user = userRepository.findByName("user.eng_2.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("engineer_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("engineer_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_trader_1_europe() {
        // Given
        User user = userRepository.findByName("user.trader_1.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("trader_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_trader_2_europe() {
        // Given
        User user = userRepository.findByName("user.trader_2.europe");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(1L, 2L);

        assertThat(extractProperty("taskCode").from(tasks.get(1L))).containsOnly("trader_user_task");
        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_team_task");
    }

    @Test
    public void should_find_all_pending_tasks_for_user_and_team_members_for_user_trader_asia() {
        // Given
        User user = userRepository.findByName("user.trader.asia");

        // When
        Map<Long, Collection<TaskShortInfo>> tasks = taskHelper.findAllPendingTasksForUserAndTeamMembers(user);

        // Then
        assertThat(tasks.keySet()).containsOnly(2L);

        assertThat(extractProperty("taskCode").from(tasks.get(2L))).containsOnly("trader_user_task");
    }

}
