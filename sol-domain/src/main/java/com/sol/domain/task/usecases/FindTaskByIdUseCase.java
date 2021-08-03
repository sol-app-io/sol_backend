package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;

/**
 * Поиск сущности
 */
public class FindTaskByIdUseCase extends AbstractFindByIdUseCase<String, TaskEntity, TaskRepository> {

    public FindTaskByIdUseCase(TaskRepository repository) {
        super(repository);
    }
}