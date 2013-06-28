package fr.xebia.xke.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.Member;
import fr.xebia.xke.model.Task;
import fr.xebia.xke.model.Team;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.TaskRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.*;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Multimaps.index;

@Component
public class TaskHelper {

    private TaskRepository taskRepository;

    @Inject
    public TaskHelper(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Tasks assigned to user, user's teams and user's teams' users and recursively to
     * users's teams' subteams.
     */
    public Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndTeamMembers(User user) {
        FluentIterable<TaskShortInfo> tasks = FluentIterable.from(ALL_TASKS())
                                                            .filter(ASSIGNED_TO_USER_OR_TEAM_MEMBERS(user))
                                                            .transform(TO_SHORT_INFO);

        return index(tasks, BY_PRODUCT_ID).asMap();
    }

    /**
     * Tasks assigned to user, user's teams and user's teams' users
     */
    public Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndUserTeams(User user) {
        FluentIterable<TaskShortInfo> tasks = FluentIterable.from(ALL_TASKS())
                                                            .filter(ASSIGNED_TO_USER_OR_USER_TEAMS(user))
                                                            .transform(TO_SHORT_INFO);

        return index(tasks, BY_PRODUCT_ID).asMap();
    }

    private static final Function<TaskShortInfo, Long> BY_PRODUCT_ID = new Function<TaskShortInfo, Long>() {

        @Nullable
        @Override
        public Long apply(TaskShortInfo task) {
            return task.getProductId();
        }

    };

    private static final Function<Team, String> TO_TEAM_NAME = new Function<Team, String>() {

        @Nullable
        @Override
        public String apply(Team team) {
            return team.getName();
        }

    };

    private static final Function<Task, TaskShortInfo> TO_SHORT_INFO = new Function<Task, TaskShortInfo>() {

        @Nullable
        @Override
        public TaskShortInfo apply(Task task) {
            return new TaskShortInfo(task);
        }

    };

    private static Predicate<Task> ASSIGNED_TO_USER_OR_TEAM_MEMBERS(final User user) {
        return new Predicate<Task>() {

            @Override
            public boolean apply(Task task) {
                return assignedToMember(task, user, user) || assignedToTeamMembers(task, user, Lists.newLinkedList(user.getTeams()));
            }

        };
    }

    private static Predicate<Task> ASSIGNED_TO_USER_OR_USER_TEAMS(final User user) {
        return new Predicate<Task>() {

            @Override
            public boolean apply(Task task) {
                return assignedToMember(task, user, user) || assignedToUserTeams(task, user, user.getTeams());
            }

        };
    }

    private static boolean assignedToMember(Task task, User user, Member member) {
        if (user.hasRole(task.getAssignedRole())) {
            if (task.getAssignedUser() != null && member instanceof User) {
                return ((User) member).getName().equals(task.getAssignedUser());
            }

            if (task.getAssignedTeam() != null && member instanceof Team) {
                return ((Team) member).getName().equals(task.getAssignedTeam());
            }
        }

        return false;
    }

    private static boolean assignedToUserTeams(Task task, User user, Set<Team> teams) {
        return user.hasRole(task.getAssignedRole()) && from(teams).transform(TO_TEAM_NAME).contains(task.getAssignedTeam());
    }

    private static boolean assignedToTeamMembers(Task task, User user, LinkedList<? extends Member> members) {
        if (members.isEmpty()) {
            return false;
        }

        Member member = members.poll();

        return assignedToMember(task, user, member)
                || assignedToTeamMembers(task, user, members)
                || (member instanceof Team && assignedToTeamMembers(task, user, Lists.newLinkedList(((Team) member).getMembers())));

    }

    private List<Task> ALL_TASKS() {
        return taskRepository.findAll();
    }

}
