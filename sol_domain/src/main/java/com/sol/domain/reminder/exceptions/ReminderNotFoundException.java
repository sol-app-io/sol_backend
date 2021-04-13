package com.sol.domain.reminder.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ReminderNotFoundException extends DomainException {

    public ReminderNotFoundException() {
        super("Reminder not found");
    }

    public ReminderNotFoundException(String id) {
        super("Reminder not found by ID: " + id);
    }
}
