package fr.xebia.xke.repository.impl;

import fr.xebia.xke.dto.TaskShortInfo;
import fr.xebia.xke.repository.TaskRepositoryCustom;
import fr.xebia.xke.repository.extractor.TaskExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static fr.xebia.xke.repository.query.TaskRepositoryQuery.GET_TASKS_FOR_USER_AND_TEAM_MEMBERS_QUERY;
import static fr.xebia.xke.repository.query.TaskRepositoryQuery.GET_TASKS_FOR_USER_AND_USER_TEAMS_QUERY;

@Repository
public class TaskRepositoryImpl extends JdbcDaoSupport implements TaskRepositoryCustom {

    @Inject
    public TaskRepositoryImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndTeamMembers(Long userId, List<String> rolesName) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("userId", userId);
        parameters.addValue("rolesName", rolesName);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());

        return namedParameterJdbcTemplate.query(GET_TASKS_FOR_USER_AND_TEAM_MEMBERS_QUERY, parameters, new TaskExtractor());
    }

    @Override
    public Map<Long, Collection<TaskShortInfo>> findAllPendingTasksForUserAndUserTeams(Long userId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("userId", userId);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());

        return namedParameterJdbcTemplate.query(GET_TASKS_FOR_USER_AND_USER_TEAMS_QUERY, parameters, new TaskExtractor());
    }

}
