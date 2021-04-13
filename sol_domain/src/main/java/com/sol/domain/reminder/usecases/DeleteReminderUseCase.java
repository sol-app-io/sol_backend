package com.sol.domain.reminder.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.reminder.port.ReminderRepository;

/**
 * Удаление сущности
 */
public class DeleteReminderUseCase extends AbstractDeleteUseCase<String, ReminderRepository> {

    public DeleteReminderUseCase(ReminderRepository repository) {
        super(repository);
    }
}
