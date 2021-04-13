package com.sol.domain.view.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class ViewNotFoundException extends DomainException {

    public ViewNotFoundException() {
        super("View not found");
    }

    public ViewNotFoundException(String id) {
        super("View not found by ID: " + id);
    }
}
