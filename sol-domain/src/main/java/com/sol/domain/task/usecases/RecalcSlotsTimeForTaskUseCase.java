package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.usecases.FindInboxSpaceByOwnerIdUseCase;
import com.sol.domain.space.usecases.FindSpaceByIdUseCase;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.domain.task.port.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Создание сущности
 */
public class RecalcSlotsTimeForTaskUseCase extends UseCase<RecalcSlotsTimeForTaskUseCase.InputValues, SingletonEntityOutputValues<TaskEntity>>{


    private final TaskRepository repository;
    private final SlotRepository slotRepository;

    public RecalcSlotsTimeForTaskUseCase(
            TaskRepository repository,
            SlotRepository slotRepository) {
        this.repository = repository;
        this.slotRepository = slotRepository;
    }

    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        TaskEntity taskEntity = repository.findById(inputValues.taskId).orElseThrow(TaskNotFoundException::new);
        List<SlotEntity> slotEntities = slotRepository.findByTaskId(inputValues.taskId);
        taskEntity.setSlotsMilliseconds(0l);
        for(SlotEntity slotEntity: slotEntities){
            taskEntity.setSlotsMilliseconds(taskEntity.getSlotsMilliseconds() + slotEntity.getSlotsMilliseconds());
        }
        taskEntity = repository.save(taskEntity);

        return SingletonEntityOutputValues.of(taskEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {

        protected String taskId;
        protected String currentUserId;

    }
}
