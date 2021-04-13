package com.sol.domain.repeatTaskConf.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class RepeatTaskConfNotFoundException extends DomainException {

    public RepeatTaskConfNotFoundException() {
        super("RepeatTaskConf not found");
    }

    public RepeatTaskConfNotFoundException(String id) {
        super("RepeatTaskConf not found by ID: " + id);
    }
}
