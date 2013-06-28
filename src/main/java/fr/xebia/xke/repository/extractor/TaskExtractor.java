package fr.xebia.xke.repository.extractor;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import fr.xebia.xke.dto.TaskShortInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class TaskExtractor implements ResultSetExtractor<Map<Long, Collection<TaskShortInfo>>> {

    @Override
    public Map<Long, Collection<TaskShortInfo>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Collection<TaskShortInfo>> tasks = Maps.newHashMap();

        while (resultSet.next()) {
            TaskShortInfo task = buildTask(resultSet);

            Long productId = task.getProductId();

            if (!tasks.containsKey(productId)) {
                tasks.put(productId, Sets.<TaskShortInfo>newHashSet());
            }

            tasks.get(productId).add(task);
        }

        return tasks;
    }

    private TaskShortInfo buildTask(ResultSet resultSet) throws SQLException {
        TaskShortInfo task = new TaskShortInfo();

        task.setId(resultSet.getLong("ID"));
        task.setTaskCode(resultSet.getString("TASKCODE"));
        task.setProductId(resultSet.getLong("PRODUCT_ID"));

        return task;
    }

}
