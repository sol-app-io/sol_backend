package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.backgroundTaskForView.entity.*;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;

/**
 * Создание сущности
 */
public class CreateBackgroundTaskForViewUseCase extends AbstractCreateUseCase<BackgroundTaskForViewEntity, BackgroundTaskForViewIdGenerator<?>, BackgroundTaskForViewRepository, CreateBackgroundTaskForViewUseCase.InputValues> {


    public CreateBackgroundTaskForViewUseCase(BackgroundTaskForViewRepository repository, BackgroundTaskForViewIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<BackgroundTaskForViewEntity> execute(InputValues inputValues) {

        BackgroundTaskForViewEntity backgroundTaskForViewEntity = new BackgroundTaskForViewEntity(idGenerator.generate());

        backgroundTaskForViewEntity.setTaskId(inputValues.taskId);
        backgroundTaskForViewEntity.setStatus(BackgroundTaskForViewEntity.Status.NEW);
        backgroundTaskForViewEntity.setLog("");

        backgroundTaskForViewEntity = repository.save(backgroundTaskForViewEntity);

        return SingletonEntityOutputValues.of(backgroundTaskForViewEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Task 
        */
        protected String taskId;
    }
}
