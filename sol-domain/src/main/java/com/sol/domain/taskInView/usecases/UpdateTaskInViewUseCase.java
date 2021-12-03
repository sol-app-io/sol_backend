package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.exceptions.TaskInViewNotFoundException;
import com.sol.domain.taskInView.port.TaskInViewRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateTaskInViewUseCase extends UseCase<UpdateTaskInViewUseCase.InputValues, SingletonEntityOutputValues<TaskInViewEntity>> {

    private final TaskInViewRepository taskInViewRepository;

    @Override
    public SingletonEntityOutputValues<TaskInViewEntity> execute(InputValues inputValues) {
        TaskInViewEntity taskInViewEntity = taskInViewRepository.findById(inputValues.getId())
                .orElseThrow(TaskInViewNotFoundException::new);

        taskInViewEntity.setViewId(inputValues.viewId);
        taskInViewEntity.setTaskId(inputValues.taskId);
        taskInViewEntity.setSortNum(inputValues.sortNum);
        
        taskInViewEntity = taskInViewRepository.save(taskInViewEntity);

        return SingletonEntityOutputValues.of(taskInViewEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        /**
        * view 
        */
        protected String viewId;
        /**
        * Task 
        */
        protected String taskId;
        /**
        * Sort num 
        */
        protected Integer sortNum;

    }
}
