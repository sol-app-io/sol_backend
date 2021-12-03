package com.sol.domain.taskInView.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.filters.TaskInViewFilters;

public interface TaskInViewRepository extends CRUDRepository<String, TaskInViewEntity, TaskInViewFilters> {
}
