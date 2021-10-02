package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.ExternalId;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.slot.exceptions.HasNoAccessToSlotException;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.usecases.RecalcSlotsTimeForTaskUseCase;
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
    private final RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase;

    @Override
    public SingletonEntityOutputValues<SlotEntity> execute(InputValues inputValues) {

        SlotEntity slotEntity = slotRepository.findById(inputValues.getId())
                .orElseThrow(SlotNotFoundException::new);

        if(slotEntity.getOwnerId().equals(inputValues.currentUserId) == false){
            throw new HasNoAccessToSlotException();
        }

        // Происходит заполнение всех полей
        slotEntity.setStartTime(DateUtils.convert(inputValues.startTime, inputValues.timezone));
        slotEntity.setEndTime(DateUtils.convert(inputValues.endTime, inputValues.timezone));
        slotEntity.setTimezone(inputValues.timezone);
        slotEntity.setSlotsMilliseconds(DateUtils.range(inputValues.startTime, inputValues.endTime));

        slotEntity = slotRepository.save(slotEntity);

        recalcSlotsTimeForTaskUseCase.execute(
                RecalcSlotsTimeForTaskUseCase
                        .InputValues.of(
                                slotEntity.getCreatedFromTaskId(),
                        inputValues.currentUserId ));

        return SingletonEntityOutputValues.of(slotEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;
        // перечисление полей необходимых для создания сущности
        protected String currentUserId;
        protected Long startTime;
        protected Long endTime;
        protected Integer timezone;
    }

}
