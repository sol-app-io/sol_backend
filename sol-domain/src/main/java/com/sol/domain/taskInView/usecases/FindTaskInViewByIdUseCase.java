package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import lombok.*;

import java.util.Optional;

/**
 * Поиск сущности
 */
@RequiredArgsConstructor
public class FindTaskInViewByIdUseCase extends UseCase<FindTaskInViewByIdUseCase.InputValues, SingletonEntityOutputValues<TaskInViewEntity>> {

    private final TaskInViewRepository taskInViewRepository;
    private final TaskInViewIdGenerator taskInViewIdGenerator;
    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<TaskInViewEntity> execute(InputValues inputValues) {
        Optional<TaskInViewEntity> taskInViewOptional = taskInViewRepository.findOne(inputValues.taskId, inputValues.viewId);

        if(taskInViewOptional.isPresent()) return SingletonEntityOutputValues.of(taskInViewOptional.get());

        TaskInViewEntity taskInViewEntity = new TaskInViewEntity(taskInViewIdGenerator.generate());
        taskInViewEntity.setViewId(inputValues.viewId);
        taskInViewEntity.setTaskId(inputValues.taskId);
        taskInViewEntity.setSortNum(taskInViewRepository.count(inputValues.viewId).intValue() + 1);

        taskInViewEntity = taskInViewRepository.save(taskInViewEntity);

        TaskEntity taskEntity = taskRepository.findById(taskInViewEntity.getTaskId()).orElseThrow(TaskNotFoundException::new);
        if(taskEntity.getViewIds().contains(inputValues.taskId)){
            taskEntity.getViewIds().add(inputValues.taskId);
            taskRepository.save(taskEntity);
        }


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
    }
}
