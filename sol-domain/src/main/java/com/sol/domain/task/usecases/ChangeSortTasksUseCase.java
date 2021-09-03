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
import java.util.ArrayList;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class ChangeSortTasksUseCase extends UseCase<ChangeSortTasksUseCase.InputValues, SingletonEntityOutputValues<List<TaskEntity>>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskEntity>> execute(InputValues inputValues) {
        List<TaskEntity> tasks = new ArrayList<>();

        for (String taskId : inputValues.tasks) {
            TaskEntity task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
            if (task.checkAccess(inputValues.currentSolUserId) == false) {
                throw new HasNoAccessToTaskException();
            }
            tasks.add(task);
        }

        for(int index = 0 ; index< tasks.size(); index++){
            TaskEntity taskEntity = tasks.get(index);
            taskEntity.setSortNum(index);
            taskRepository.save(taskEntity);
        }

        return SingletonEntityOutputValues.of(tasks);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected List<String> tasks;
        @NotBlank
        protected String currentSolUserId;
    }
}
