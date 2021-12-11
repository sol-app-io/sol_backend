package com.sol.domain.taskInView.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.filters.TaskInViewFilters;

import java.util.List;
import java.util.Optional;

public interface TaskInViewRepository extends CRUDRepository<String, TaskInViewEntity, TaskInViewFilters> {
    void removeByViewId(String viewId);
    Optional<TaskInViewEntity> findOne(String taskId, String viewId);
    Long count(String viewId);
    List<TaskInViewEntity> findByTaskId(String taskId);
}
