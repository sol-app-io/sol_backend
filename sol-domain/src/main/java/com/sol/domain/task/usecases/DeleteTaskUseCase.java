package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.task.port.TaskRepository;

/**
 * Удаление сущности
 */
public class DeleteTaskUseCase extends AbstractDeleteUseCase<String, TaskRepository> {

    public DeleteTaskUseCase(TaskRepository repository) {
        super(repository);
    }
}
