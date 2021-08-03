package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.task.port.filters.TaskFilters;

/**
 * Поиск по коллекции
 */
public class FindTaskUseCase extends AbstractFindWithFiltersUseCase<TaskEntity, TaskFilters, TaskRepository> {

    public FindTaskUseCase(TaskRepository repository) {
        super(repository);
    }
}