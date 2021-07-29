package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.SlotRepository;

/**
 * Поиск сущности
 */
public class FindSlotByIdUseCase extends AbstractFindByIdUseCase<String, SlotEntity, SlotRepository> {

    public FindSlotByIdUseCase(SlotRepository repository) {
        super(repository);
    }
}