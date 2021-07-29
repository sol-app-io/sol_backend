package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.ExternalId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.slot.entity.*;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.domain.slot.port.SlotRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Создание сущности
 */
public class CreateSlotUseCase extends AbstractCreateUseCase<SlotEntity, SlotIdGenerator, SlotRepository, CreateSlotUseCase.InputValues> {


    public CreateSlotUseCase(SlotRepository repository, SlotIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<SlotEntity> execute(InputValues inputValues) {

        validate(inputValues);

        SlotEntity slotEntity = new SlotEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        slotEntity.setOwnerId(inputValues.ownerId);
        slotEntity.setCreatedFromTaskId(inputValues.createdFromTaskId);
        slotEntity.setSpaceId(inputValues.spaceId);
        slotEntity.setViewIds(inputValues.viewIds);
        slotEntity.setDay(inputValues.day);
        slotEntity.setStartTime(inputValues.startTime);
        slotEntity.setEndTime(inputValues.endTime);
        slotEntity.setPoints(inputValues.points);
        slotEntity.setExternalIds(inputValues.externalIds);

        slotEntity = repository.save(slotEntity);

        return SingletonEntityOutputValues.of(slotEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected String createdFromTaskId;
        protected String spaceId;
        protected String viewIds;
        protected LocalDate day;
        protected LocalDateTime startTime;
        protected LocalDateTime endTime;
        protected Long points;
        protected List<ExternalId> externalIds;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
