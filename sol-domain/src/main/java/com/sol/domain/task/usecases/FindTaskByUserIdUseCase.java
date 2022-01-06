package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
import com.sol.domain.task.port.TaskRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindTaskByUserIdUseCase extends UseCase<FindTaskByUserIdUseCase.InputValues, SingletonEntityOutputValues<List<TaskEntity>>> {

    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<List<TaskEntity>> execute(InputValues inputValues) {
        List<TaskEntity> taskEntities = taskRepository.findByUserId(inputValues.currentSolUserId);
//        for (TaskEntity taskEntity : taskEntities) {
//            if(taskEntity.checkAccess(inputValues.currentSolUserId) == false) throw new HasNoAccessToTaskException();
//        }
        return SingletonEntityOutputValues.of(taskEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String currentSolUserId;
    }

}
