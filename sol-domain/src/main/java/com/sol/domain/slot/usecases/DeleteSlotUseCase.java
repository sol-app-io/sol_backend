package com.sol.domain.slot.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.slot.port.SlotRepository;

/**
 * Удаление сущности
 */
public class DeleteSlotUseCase extends AbstractDeleteUseCase<String, SlotRepository> {

    public DeleteSlotUseCase(SlotRepository repository) {
        super(repository);
    }
}
