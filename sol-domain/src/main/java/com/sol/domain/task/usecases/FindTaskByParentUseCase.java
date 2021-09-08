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
public class FindTaskByParentUseCase extends UseCase<FindTaskByParentUseCase.InputValues, SingletonEntityOutputValues<List<TaskEntity>>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskEntity>> execute(InputValues inputValues) {
        List<TaskEntity> taskEntities = taskRepository.findByParentId(inputValues.getParentId(), TaskStatus.open());
        if(taskEntities.size() > 0) {
            if(taskEntities.get(0).checkAccess(inputValues.solUserId) == false) {
                throw new HasNoAccessToTaskException();
            }
        }

        return SingletonEntityOutputValues.of(taskEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String parentId;
        @NotBlank
        protected String solUserId;
    }
}
