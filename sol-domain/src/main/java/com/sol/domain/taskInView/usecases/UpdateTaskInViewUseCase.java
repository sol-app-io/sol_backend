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

    protected final FindTaskInViewByIdUseCase findTaskInViewByIdUseCase;
    private final TaskInViewRepository taskInViewRepository;

    @Override
    public SingletonEntityOutputValues<TaskInViewEntity> execute(InputValues inputValues) {
        TaskInViewEntity taskInViewEntity = findTaskInViewByIdUseCase.execute(FindTaskInViewByIdUseCase.InputValues.of(
                inputValues.viewId, inputValues.taskId
        )).getEntity();

        taskInViewEntity.setSortNum(inputValues.sortNum);

        taskInViewEntity = taskInViewRepository.save(taskInViewEntity);

        return SingletonEntityOutputValues.of(taskInViewEntity);
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
        /**
         * Sort num
         */
        protected Integer sortNum;
    }
}
