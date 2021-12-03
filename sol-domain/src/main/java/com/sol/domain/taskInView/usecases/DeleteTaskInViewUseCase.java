package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.taskInView.port.TaskInViewRepository;

/**
 * Удаление сущности
 */
public class DeleteTaskInViewUseCase extends AbstractDeleteUseCase<String, TaskInViewRepository> {

    public DeleteTaskInViewUseCase(TaskInViewRepository repository) {
        super(repository);
    }
}
