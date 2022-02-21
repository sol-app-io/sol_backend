package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.space.usecases.UpdateTaskCountInSpaceUseCase;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class MakeTaskOpenUseCase extends UseCase<MakeTaskOpenUseCase.InputValues, SingletonEntityOutputValues<TaskEntity>> {

    private final TaskRepository taskRepository;
    private final UpdateTaskCountInSpaceUseCase updateTaskCountInSlotUseCase;
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;
    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        TaskEntity taskEntity = taskRepository.findById(inputValues.getId())
                .orElseThrow(TaskNotFoundException::new);

        if (taskEntity.checkAccess(inputValues.solUserId) == false) {
            throw new HasNoAccessToTaskException();
        }

        taskEntity.setStatus(TaskStatus.OPEN);
        taskEntity = taskRepository.save(taskEntity);

        updateTaskCountInSlotUseCase.execute(UpdateTaskCountInSpaceUseCase.InputValues.of(taskEntity.getSpaceId()));
        createBackgroundTaskForViewUseCase.execute(CreateBackgroundTaskForViewUseCase.InputValues.of(taskEntity.getId()));
        return SingletonEntityOutputValues.of(taskEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        protected String solUserId;
    }
}
