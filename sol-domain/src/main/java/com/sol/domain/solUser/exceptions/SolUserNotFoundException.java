package com.sol.domain.solUser.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class SolUserNotFoundException extends DomainException {

    public SolUserNotFoundException() {
        super("SolUser not found");
    }

    public SolUserNotFoundException(String id) {
        super("SolUser not found by ID: " + id);
    }
}
