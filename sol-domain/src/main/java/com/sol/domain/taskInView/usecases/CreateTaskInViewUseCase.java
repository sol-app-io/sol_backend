package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import lombok.*;

/**
 * Создание сущности
 */
@RequiredArgsConstructor
public class CreateTaskInViewUseCase extends UseCase<CreateTaskInViewUseCase.InputValues, SingletonEntityOutputValues<TaskInViewEntity>> {

    protected final FindTaskInViewByIdUseCase findTaskInViewByIdUseCase;
    protected final TaskInViewRepository taskInViewRepository;
    protected final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<TaskInViewEntity> execute(InputValues inputValues) {

        TaskInViewEntity taskInViewEntity = findTaskInViewByIdUseCase.execute(FindTaskInViewByIdUseCase.InputValues.of(
                inputValues.viewId, inputValues.taskId
        )).getEntity();

        taskInViewEntity.setViewId(inputValues.viewId);
        taskInViewEntity.setTaskId(inputValues.taskId);
        if(inputValues.sortNum != null){
            taskInViewEntity.setSortNum(inputValues.sortNum);
        }

        taskInViewEntity = taskInViewRepository.save(taskInViewEntity);

        TaskEntity taskEntity = taskRepository.findById(inputValues.taskId).get();
        taskEntity.getViewIds().add(inputValues.viewId);
        taskEntity.getSuggestForViewIds().remove(inputValues.getViewId());
        taskRepository.save(taskEntity);

        return SingletonEntityOutputValues.of(taskInViewEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
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
