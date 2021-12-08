package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.taskInView.port.filters.TaskInViewFilters;

/**
 * Поиск по коллекции
 */
public class FindTaskInViewUseCase extends AbstractFindWithFiltersUseCase<TaskInViewEntity, TaskInViewFilters, TaskInViewRepository> {

    public FindTaskInViewUseCase(TaskInViewRepository repository) {
        super(repository);
    }
}