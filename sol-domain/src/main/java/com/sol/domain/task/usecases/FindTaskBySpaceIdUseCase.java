package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindTaskBySpaceIdUseCase extends UseCase<FindTaskBySpaceIdUseCase.InputValues, SingletonEntityOutputValues<List<TaskEntity>>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskEntity>> execute(InputValues inputValues) {
        List<TaskEntity> taskEntity = taskRepository.findBySpaceId(inputValues.spaceId);

        return SingletonEntityOutputValues.of(taskEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String spaceId;

    }
}
