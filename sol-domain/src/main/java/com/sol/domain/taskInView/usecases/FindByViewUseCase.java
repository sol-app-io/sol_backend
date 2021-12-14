package com.sol.domain.taskInView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import lombok.*;

import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindByViewUseCase extends UseCase<FindByViewUseCase.InputValues, SingletonEntityOutputValues<List<TaskInViewEntity>>> {

    private final TaskInViewRepository taskInViewRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskInViewEntity>> execute(InputValues inputValues) {
        List<TaskInViewEntity> taskInViewEntities = taskInViewRepository.findByViewId(inputValues.viewId);
        return SingletonEntityOutputValues.of(taskInViewEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {

        /**
         * View
         */
        protected String viewId;

    }
}
