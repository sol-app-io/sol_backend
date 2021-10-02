package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.ExternalId;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.usecases.RecalcSlotsTimeForTaskUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.slot.entity.*;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.domain.slot.port.SlotRepository;

import java.time.*;
import java.util.List;

/**
 * Создание сущности
 */
public class CreateSlotUseCase extends AbstractCreateUseCase<SlotEntity, SlotIdGenerator, SlotRepository, CreateSlotUseCase.InputValues> {

    private final RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase;

    public CreateSlotUseCase(SlotRepository repository, SlotIdGenerator idGenerator, RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase) {
        super(repository, idGenerator);
        this.recalcSlotsTimeForTaskUseCase = recalcSlotsTimeForTaskUseCase;
    }

    @Override
    public SingletonEntityOutputValues<SlotEntity> execute(InputValues inputValues) {

        validate(inputValues);

        SlotEntity slotEntity = new SlotEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        slotEntity.setOwnerId(inputValues.ownerId);
        slotEntity.setTitle(inputValues.createdFromTask.getTitle());
        slotEntity.setCreatedFromTaskId(inputValues.createdFromTask.getId());
        slotEntity.setSpaceId(inputValues.createdFromTask.getSpaceId());
        slotEntity.setViewIds(inputValues.createdFromTask.getViewIds());
        slotEntity.setStartTime(DateUtils.convert(inputValues.startTime, inputValues.timezone));
        slotEntity.setEndTime(DateUtils.convert(inputValues.endTime, inputValues.timezone));
        slotEntity.setTimezone(inputValues.timezone);
        slotEntity.setSlotsMilliseconds(DateUtils.range(inputValues.startTime, inputValues.endTime));

        slotEntity = repository.save(slotEntity);

        recalcSlotsTimeForTaskUseCase.execute(RecalcSlotsTimeForTaskUseCase.InputValues.of(inputValues.createdFromTask.getId(), inputValues.ownerId ));

        return SingletonEntityOutputValues.of(slotEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected TaskEntity createdFromTask;
        protected Long startTime;
        protected Long endTime;
        protected Integer timezone;
    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
