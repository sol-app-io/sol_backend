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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindByDateSlotUseCase extends UseCase<FindByDateSlotUseCase.InputValues, SingletonEntityOutputValues<List<SlotEntity>>> {

    private final SlotRepository slotRepository;

    @Override
    public SingletonEntityOutputValues<List<SlotEntity>> execute(InputValues inputValues) {

        LocalDateTime dateTime = DateUtils.convert(inputValues.date, inputValues.timezone);
        LocalDateTime start = dateTime.toLocalDate().atTime(LocalTime.MIN);
        LocalDateTime end = dateTime.toLocalDate().atTime(LocalTime.MAX);

        List<SlotEntity> slots = slotRepository.findByTime(start, end, inputValues.currentUserId);

        return SingletonEntityOutputValues.of(slots);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        protected String currentUserId;
        protected Long date;
        protected Integer timezone;
    }

}
