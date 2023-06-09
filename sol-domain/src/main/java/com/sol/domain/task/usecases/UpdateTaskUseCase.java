package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskStatus;
import lombok.*;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateTaskUseCase extends UseCase<UpdateTaskUseCase.InputValues, SingletonEntityOutputValues<TaskEntity>> {

    private final TaskRepository taskRepository;
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;
    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        TaskEntity taskEntity = taskRepository.findById(inputValues.getId())
                .orElseThrow(TaskNotFoundException::new);

        taskEntity.setParentTaskId(inputValues.parentTaskId);
        taskEntity.setSpaceId(inputValues.spaceId);
        taskEntity.setTitle(inputValues.title);
        taskEntity.setIcon(inputValues.icon);
        taskEntity.setDeadline(inputValues.deadline);
        taskEntity.setRepeatTaskConfId(inputValues.repeatTaskConfId);
        taskEntity.setCreatedFromRepeatTaskId(inputValues.createdFromRepeatTaskId);
        taskEntity.setPics(inputValues.pics);
        taskEntity.setFiles(inputValues.files);
        taskEntity.setDescription(inputValues.description);
        taskEntity.setExternalIds(inputValues.externalIds);
        taskEntity.setStatus(inputValues.status);
        
        taskEntity = taskRepository.save(taskEntity);

        createBackgroundTaskForViewUseCase
                .execute(
                        CreateBackgroundTaskForViewUseCase.InputValues.of(taskEntity.getId())
                );

        return SingletonEntityOutputValues.of(taskEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        /**
        * parentTaskId 
        */
        protected String parentTaskId;
        /**
        * spaceId 
        */
        protected String spaceId;
        /**
        * Заголовок 
        */
        protected String title;
        /**
        * icon 
        */
        protected Icon icon;
        
        /**
        * planningPoints 
        */
        protected List<String> planningPoints;
        /**
        * deadline 
        */
        protected LocalDateTime deadline;
        /**
        * repeatTaskConfId 
        */
        protected String repeatTaskConfId;
        /**
        * createdFromRepeatTaskId 
        */
        protected String createdFromRepeatTaskId;
        /**
        * pics 
        */
        protected List<String> pics;
        /**
        * files 
        */
        protected List<String> files;
        /**
        * description 
        */
        protected String description;
        /**
        * externalIds 
        */
        protected List<String> externalIds;
        /**
        * status 
        */
        protected TaskStatus status;

    }
}
