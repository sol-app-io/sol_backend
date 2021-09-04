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
import org.springframework.scheduling.config.Task;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindTaskBySpaceIdUseCase extends UseCase<FindTaskBySpaceIdUseCase.InputValues, SingletonEntityOutputValues<List<TaskEntity.TaskWithChildEntity>>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskEntity.TaskWithChildEntity>> execute(InputValues inputValues) {
        List<TaskEntity> taskEntities = taskRepository.findBySpaceId(inputValues.spaceId);
        List<TaskEntity.TaskWithChildEntity> response = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            if(taskEntity.checkAccess(inputValues.currentSolUserId) == false) throw new HasNoAccessToTaskException();

            TaskEntity.TaskWithChildEntity task = TaskEntity.TaskWithChildEntity.of(taskEntity);
            task.setChild(taskRepository.findByParentId(task.getId()).stream().map(o -> TaskEntity.TaskWithChildEntity.of(o)).collect(Collectors.toList()));
            response.add(task);
        }
        return SingletonEntityOutputValues.of(response);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String spaceId;
        protected String currentSolUserId;
    }

}
