package fr.xebia.xke.repository;

import fr.xebia.xke.dto.TaskShortInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TaskRepositoryCustom {

    Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndTeamMembers(Long userId, List<String> rolesName);

    Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndUserTeams(Long userId);

}
