package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
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
public class DeleteAllTaskInViewByViewUseCase extends UseCase<DeleteAllTaskInViewByViewUseCase.InputValues, VoidOutputValues> {

    private final TaskInViewRepository taskInViewRepository;
    private final TaskRepository taskRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        List<TaskInViewEntity> taskInViewEntities = taskInViewRepository.findByViewId(inputValues.viewId);
        for(TaskInViewEntity taskInViewEntity: taskInViewEntities){
            Optional<TaskEntity> taskEntityOptional = taskRepository.findById(taskInViewEntity.getTaskId());
            if(taskEntityOptional.isPresent()){
                TaskEntity taskEntity = taskEntityOptional.get();
                taskEntity.getViewIds().remove(taskInViewEntity.getViewId());
                taskRepository.save(taskEntity);
            }
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
        protected String viewId;
    }
}