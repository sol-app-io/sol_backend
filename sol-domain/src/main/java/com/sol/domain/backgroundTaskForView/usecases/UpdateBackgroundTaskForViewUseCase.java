package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.exceptions.BackgroundTaskForViewNotFoundException;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateBackgroundTaskForViewUseCase extends UseCase<UpdateBackgroundTaskForViewUseCase.InputValues, SingletonEntityOutputValues<BackgroundTaskForViewEntity>> {

    private final BackgroundTaskForViewRepository backgroundTaskForViewRepository;

    @Override
    public SingletonEntityOutputValues<BackgroundTaskForViewEntity> execute(InputValues inputValues) {
        BackgroundTaskForViewEntity backgroundTaskForViewEntity = backgroundTaskForViewRepository.findById(inputValues.getId())
                .orElseThrow(BackgroundTaskForViewNotFoundException::new);

        backgroundTaskForViewEntity.setTaskId(inputValues.taskId);
        backgroundTaskForViewEntity.setStatus(inputValues.status);
        backgroundTaskForViewEntity.setLog(inputValues.log);
        
        backgroundTaskForViewEntity = backgroundTaskForViewRepository.save(backgroundTaskForViewEntity);

        return SingletonEntityOutputValues.of(backgroundTaskForViewEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        /**
        * Task 
        */
        protected String taskId;
        /**
        * status 
        */
        protected BackgroundTaskForViewEntity.Status status;
        /**
        * log 
        */
        protected String log;

    }
}
