package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.taskInView.entity.*;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.domain.taskInView.port.TaskInViewRepository;

/**
 * Создание сущности
 */
public class CreateTaskInViewUseCase extends AbstractCreateUseCase<TaskInViewEntity, TaskInViewIdGenerator<?>, TaskInViewRepository, CreateTaskInViewUseCase.InputValues> {


    public CreateTaskInViewUseCase(TaskInViewRepository repository, TaskInViewIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<TaskInViewEntity> execute(InputValues inputValues) {

        TaskInViewEntity taskInViewEntity = new TaskInViewEntity(idGenerator.generate());

        taskInViewEntity.setViewId(inputValues.viewId);
        taskInViewEntity.setTaskId(inputValues.taskId);
        taskInViewEntity.setSortNum(inputValues.sortNum);

        taskInViewEntity = repository.save(taskInViewEntity);

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
