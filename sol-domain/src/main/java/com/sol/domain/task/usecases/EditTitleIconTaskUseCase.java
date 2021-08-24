package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
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
public class EditTitleIconTaskUseCase extends UseCase<EditTitleIconTaskUseCase.InputValues, SingletonEntityOutputValues<TaskEntity>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        TaskEntity taskEntity = taskRepository.findById(inputValues.getId())
                .orElseThrow(TaskNotFoundException::new);

        if (taskEntity.getOwnerId().equals(inputValues.ownerId) == false) {
            throw new HasNoAccessToTaskException();
        }

        taskEntity.setTitle(inputValues.title);
        taskEntity.setIcon(inputValues.icon);
        taskEntity = taskRepository.save(taskEntity);

        return SingletonEntityOutputValues.of(taskEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        protected String id;
        /**
         * Заголовок
         */
        protected String title;
        /**
         * icon
         */
        protected Icon icon;

        protected String ownerId;
    }
}
