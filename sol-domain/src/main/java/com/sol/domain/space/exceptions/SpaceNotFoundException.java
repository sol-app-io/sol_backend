package com.sol.domain.space.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class SpaceNotFoundException extends DomainException {

    public SpaceNotFoundException() {
        super("Space not found");
    }

    public SpaceNotFoundException(String id) {
        super("Space not found by ID: " + id);
    }
}
