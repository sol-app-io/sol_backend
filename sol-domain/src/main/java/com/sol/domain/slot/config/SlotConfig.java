package com.sol.domain.slot.config;

import com.sol.domain.slot.usecases.*;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.task.usecases.RecalcSlotsTimeForTaskUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.domain.slot.port.SlotRepository;

@Accessors(fluent = true)
@Getter
public class SlotConfig {
    private final CreateSlotUseCase createSlotUseCase;
    private final DeleteSlotUseCase deleteSlotUseCase;
    private final FindSlotByIdUseCase findSlotByIdUseCase;
    private final UpdateSlotUseCase updateSlotUseCase;
    private final FindByDateSlotUseCase findByDateSlotUseCase;
    private final FindByTaskSlotUseCase findByTaskSlotUseCase;

    public SlotConfig(SlotRepository slotRepository, SlotIdGenerator<?> slotIdGenerator, RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase, TaskRepository taskRepository) {
        this.createSlotUseCase = new CreateSlotUseCase(slotRepository, slotIdGenerator, recalcSlotsTimeForTaskUseCase);
        this.deleteSlotUseCase = new DeleteSlotUseCase(slotRepository, recalcSlotsTimeForTaskUseCase);
        this.findSlotByIdUseCase = new FindSlotByIdUseCase(slotRepository);
        this.updateSlotUseCase = new UpdateSlotUseCase(slotRepository, recalcSlotsTimeForTaskUseCase);
        this.findByDateSlotUseCase = new FindByDateSlotUseCase(slotRepository);
        this.findByTaskSlotUseCase = new FindByTaskSlotUseCase(slotRepository);
    }
}
