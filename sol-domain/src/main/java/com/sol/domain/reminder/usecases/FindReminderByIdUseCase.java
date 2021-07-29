package com.sol.domain.reminder.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.reminder.entity.ReminderEntity;
import com.sol.domain.reminder.port.ReminderRepository;

/**
 * Поиск сущности
 */
public class FindReminderByIdUseCase extends AbstractFindByIdUseCase<String, ReminderEntity, ReminderRepository> {

    public FindReminderByIdUseCase(ReminderRepository repository) {
        super(repository);
    }
}