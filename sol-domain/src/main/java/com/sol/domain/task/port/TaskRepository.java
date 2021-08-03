package com.sol.domain.task.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.filters.TaskFilters;

import java.util.List;

public interface TaskRepository extends CRUDRepository<String, TaskEntity, TaskFilters> {
    List<TaskEntity> findBySpaceId(String spaceId);
}
