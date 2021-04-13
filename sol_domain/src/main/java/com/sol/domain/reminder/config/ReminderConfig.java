package com.sol.domain.reminder.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.reminder.port.ReminderIdGenerator;
import com.sol.domain.reminder.port.ReminderRepository;
import com.sol.domain.reminder.usecases.CreateReminderUseCase;
import com.sol.domain.reminder.usecases.DeleteReminderUseCase;
import com.sol.domain.reminder.usecases.FindReminderByIdUseCase;
import com.sol.domain.reminder.usecases.UpdateReminderUseCase;

@Accessors(fluent = true)
@Getter
public class ReminderConfig {
    private final CreateReminderUseCase createReminderUseCase;
    private final DeleteReminderUseCase deleteReminderUseCase;
    private final FindReminderByIdUseCase findReminderByIdUseCase;
    private final UpdateReminderUseCase updateReminderUseCase;

    public ReminderConfig(ReminderRepository reminderRepository, ReminderIdGenerator<?> reminderIdGenerator) {
        this.createReminderUseCase = new CreateReminderUseCase(reminderRepository, reminderIdGenerator);
        this.deleteReminderUseCase = new DeleteReminderUseCase(reminderRepository);
        this.findReminderByIdUseCase = new FindReminderByIdUseCase(reminderRepository);
        this.updateReminderUseCase = new UpdateReminderUseCase(reminderRepository);
    }
}
