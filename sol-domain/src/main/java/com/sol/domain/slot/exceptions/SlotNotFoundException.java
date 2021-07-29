package com.sol.domain.slot.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class SlotNotFoundException extends DomainException {

    public SlotNotFoundException() {
        super("Slot not found");
    }

    public SlotNotFoundException(String id) {
        super("Slot not found by ID: " + id);
    }
}
