package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.exceptions.HasNoAccessToSlotException;
import com.sol.domain.slot.exceptions.SlotNotFoundException;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.task.usecases.RecalcSlotsTimeForTaskUseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSlotUseCase extends UseCase<DeleteSlotUseCase.InputValues, SingletonEntityOutputValues<Boolean>> {

    private final SlotRepository slotRepository;
    private final RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase;

    @Override
    public SingletonEntityOutputValues<Boolean> execute(InputValues inputValues) {

        SlotEntity slotEntity = slotRepository.findById(inputValues.getId())
                .orElseThrow(SlotNotFoundException::new);

        if(slotEntity.getOwnerId().equals(inputValues.currentUserId) == false){
            throw new HasNoAccessToSlotException();
        }

        slotRepository.delete(slotEntity.getId());

        recalcSlotsTimeForTaskUseCase.execute(
                RecalcSlotsTimeForTaskUseCase
                        .InputValues.of(
                        slotEntity.getCreatedFromTaskId(),
                        inputValues.currentUserId ));

        return SingletonEntityOutputValues.of(true);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;
        // перечисление полей необходимых для создания сущности
        protected String currentUserId;
    }

}
