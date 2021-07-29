package com.sol.domain.reminder.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.reminder.entity.ReminderEntity;
import com.sol.domain.reminder.port.filters.ReminderFilters;

public interface ReminderRepository extends CRUDRepository<String, ReminderEntity, ReminderFilters> {
}
