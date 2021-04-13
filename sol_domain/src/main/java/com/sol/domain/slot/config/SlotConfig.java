package com.sol.domain.slot.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.slot.usecases.CreateSlotUseCase;
import com.sol.domain.slot.usecases.DeleteSlotUseCase;
import com.sol.domain.slot.usecases.FindSlotByIdUseCase;
import com.sol.domain.slot.usecases.UpdateSlotUseCase;

@Accessors(fluent = true)
@Getter
public class SlotConfig {
    private final CreateSlotUseCase createSlotUseCase;
    private final DeleteSlotUseCase deleteSlotUseCase;
    private final FindSlotByIdUseCase findSlotByIdUseCase;
    private final UpdateSlotUseCase updateSlotUseCase;

    public SlotConfig(SlotRepository slotRepository, SlotIdGenerator<?> slotIdGenerator) {
        this.createSlotUseCase = new CreateSlotUseCase(slotRepository, slotIdGenerator);
        this.deleteSlotUseCase = new DeleteSlotUseCase(slotRepository);
        this.findSlotByIdUseCase = new FindSlotByIdUseCase(slotRepository);
        this.updateSlotUseCase = new UpdateSlotUseCase(slotRepository);
    }
}
