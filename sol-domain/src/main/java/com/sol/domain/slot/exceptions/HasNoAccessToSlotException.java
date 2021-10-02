package com.sol.domain.slot.exceptions;

import com.rcore.domain.commons.exception.BadRequestDomainException;

public class HasNoAccessToSlotException  extends BadRequestDomainException {

    public HasNoAccessToSlotException() {
        super(
                "HasNoAccessToSlotException"
        );
    }

    public HasNoAccessToSlotException(String id) {
        super(
                "HasNoAccessToSlotException"
        );
    }
}

