package fr.xebia.xke.service.impl;

import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.model.User;
import fr.xebia.xke.repository.TaskRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Map;

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
        return taskRepository.findAllPendingTasksForUserAndTeamMembers(user.getId(), user.getRolesName());
    }

    /**
     * Tasks assigned to user, user's teams and user's teams' users
     */
    public Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndUserTeams(User user) {
        return taskRepository.findAllPendingTasksForUserAndUserTeams(user.getId());
    }

}
