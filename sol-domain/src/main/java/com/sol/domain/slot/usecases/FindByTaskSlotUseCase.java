package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.exceptions.HasNoAccessToSlotException;
import com.sol.domain.slot.exceptions.SlotNotFoundException;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.task.usecases.RecalcSlotsTimeForTaskUseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindByTaskSlotUseCase extends UseCase<FindByTaskSlotUseCase.InputValues, SingletonEntityOutputValues<List<SlotEntity>>> {

    private final SlotRepository slotRepository;

    @Override
    public SingletonEntityOutputValues<List<SlotEntity>> execute(InputValues inputValues) {

        List<SlotEntity> slots = slotRepository.findByTaskId(inputValues.taskId, inputValues.currentUserId);
        return SingletonEntityOutputValues.of(slots);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        protected String currentUserId;
        protected String taskId;
    }

}
