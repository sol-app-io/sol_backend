package com.sol.domain.solUser.exceptions;

import com.rcore.domain.commons.exception.DomainException;
import com.rcore.domain.commons.exception.NotFoundDomainException;

public class SolUserNotFoundException extends NotFoundDomainException {

    public SolUserNotFoundException() {
        super("SolUser not found");
    }

    public SolUserNotFoundException(String id) {
        super("SolUser not found by ID: " + id);
    }
}
