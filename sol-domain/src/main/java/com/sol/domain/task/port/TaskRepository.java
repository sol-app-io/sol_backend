package com.sol.domain.task.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.port.filters.TaskFilters;

import java.util.List;

public interface TaskRepository extends CRUDRepository<String, TaskEntity, TaskFilters> {
    List<TaskEntity> findBySpaceId(String spaceId, List<TaskStatus> statuses);

    List<TaskEntity> findByParentId(String parentId, List<TaskStatus> statuses);

    Long countBySpaceId(String spaceId, List<TaskStatus> statuses);

    Long countByParentId(String parentId, List<TaskStatus> statuses);
}