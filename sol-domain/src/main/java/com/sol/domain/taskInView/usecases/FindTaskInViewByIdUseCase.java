package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;

/**
 * Поиск сущности
 */
public class FindTaskInViewByIdUseCase extends AbstractFindByIdUseCase<String, TaskInViewEntity, TaskInViewRepository> {

    public FindTaskInViewByIdUseCase(TaskInViewRepository repository) {
        super(repository);
    }
}