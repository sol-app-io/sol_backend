package com.sol.domain.viewUser.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class ViewUserNotFoundException extends NotFoundDomainException {

    public ViewUserNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public ViewUserNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
