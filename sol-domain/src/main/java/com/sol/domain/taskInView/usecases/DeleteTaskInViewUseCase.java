package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import lombok.*;

/**
 * Удаление сущности
 */
@RequiredArgsConstructor
public class DeleteTaskInViewUseCase extends UseCase<DeleteTaskInViewUseCase.InputValues, VoidOutputValues> {

    protected final FindTaskInViewByIdUseCase findTaskInViewByIdUseCase;
    private final TaskInViewRepository taskInViewRepository;
    private final TaskRepository taskRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        TaskInViewEntity taskInViewEntity = findTaskInViewByIdUseCase.execute(FindTaskInViewByIdUseCase.InputValues.of(
                inputValues.viewId, inputValues.taskId
        )).getEntity();

        taskInViewRepository.delete(taskInViewEntity.getId());

        TaskEntity taskEntity = taskRepository.findById(taskInViewEntity.getTaskId()).orElseThrow(TaskNotFoundException::new);
        if(taskEntity.getViewIds().contains(inputValues.taskId)){
            taskEntity.getViewIds().remove(inputValues.taskId);
            taskRepository.save(taskEntity);
        }

        return new VoidOutputValues();
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        /**
         * view
         */
        protected String viewId;
        /**
         * Task
         */
        protected String taskId;
    }
}