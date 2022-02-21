package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import lombok.*;

import java.util.List;
import java.util.Optional;

/**
 * Удаление сущности
 */
@RequiredArgsConstructor
public class DeleteAllTaskInViewByTaskUseCase extends UseCase<DeleteAllTaskInViewByTaskUseCase.InputValues, VoidOutputValues> {

    private final TaskInViewRepository taskInViewRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        List<TaskInViewEntity> taskInViewEntities = taskInViewRepository.findByTaskId(inputValues.taskId);
        for(TaskInViewEntity taskInViewEntity: taskInViewEntities){
            taskInViewRepository.delete(taskInViewEntity.getId());
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
        protected String taskId;
    }
}