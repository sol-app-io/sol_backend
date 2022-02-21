package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.usecases.DeleteAllTaskInViewByTaskUseCase;

/**
 * Удаление сущности
 */
public class DeleteTaskUseCase extends AbstractDeleteUseCase<String, TaskRepository> {

    private final DeleteAllTaskInViewByTaskUseCase deleteAllTaskInViewByTaskUseCase;

    public DeleteTaskUseCase(
            TaskRepository repository,
            DeleteAllTaskInViewByTaskUseCase deleteAllTaskInViewByTaskUseCase
            ) {
        super(repository);
        this.deleteAllTaskInViewByTaskUseCase = deleteAllTaskInViewByTaskUseCase;
    }
    //тут удалять нужно


    @Override
    public OutputValues execute(IdInputValues<String> idInputValues) {
        deleteAllTaskInViewByTaskUseCase.execute(DeleteAllTaskInViewByTaskUseCase.InputValues.of(idInputValues.getId()));
        return super.execute(idInputValues);
    }
}
