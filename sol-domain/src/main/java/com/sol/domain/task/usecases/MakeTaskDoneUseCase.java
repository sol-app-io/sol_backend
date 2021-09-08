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
public class MakeTaskDoneUseCase extends UseCase<MakeTaskDoneUseCase.InputValues, SingletonEntityOutputValues<TaskEntity>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        TaskEntity taskEntity = taskRepository.findById(inputValues.getId())
                .orElseThrow(TaskNotFoundException::new);

        if (taskEntity.checkAccess(inputValues.solUserId) == false) {
            throw new HasNoAccessToTaskException();
        }

        if(taskEntity.getStatus().equals(TaskStatus.DONE)) return SingletonEntityOutputValues.of(taskEntity);

        taskEntity.setStatus(TaskStatus.DONE);
        taskEntity = taskRepository.save(taskEntity);

        List<TaskEntity> taskEntities = taskRepository.findByParentId(taskEntity.getId(), TaskStatus.open());
        for (TaskEntity child : taskEntities) {
            execute(InputValues.of(child.getId(), inputValues.solUserId));
        }

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
