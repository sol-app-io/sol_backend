package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.ExternalId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.exceptions.SlotNotFoundException;
import com.sol.domain.slot.port.SlotRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateSlotUseCase extends UseCase<UpdateSlotUseCase.InputValues, SingletonEntityOutputValues<SlotEntity>> {

    private final SlotRepository slotRepository;

    @Override
    public SingletonEntityOutputValues<SlotEntity> execute(InputValues inputValues) {

        SlotEntity slotEntity = slotRepository.findById(inputValues.getId())
                .orElseThrow(SlotNotFoundException::new);

        // Тут изменение данных
        slotEntity.setOwnerId(inputValues.ownerId);
        slotEntity.setCreatedFromTaskId(inputValues.createdFromTaskId);
        slotEntity.setSpaceId(inputValues.spaceId);
        slotEntity.setViewIds(inputValues.viewIds);
        slotEntity.setDay(inputValues.day);
        slotEntity.setStartTime(inputValues.startTime);
        slotEntity.setEndTime(inputValues.endTime);
        slotEntity.setPoints(inputValues.points);
        slotEntity.setExternalIds(inputValues.externalIds);
        
        slotEntity = slotRepository.save(slotEntity);

        return SingletonEntityOutputValues.of(slotEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
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

}
