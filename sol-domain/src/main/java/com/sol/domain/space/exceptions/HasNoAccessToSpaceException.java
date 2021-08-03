package com.sol.domain.space.exceptions;

import com.rcore.domain.commons.exception.BadRequestDomainException;
import com.rcore.domain.commons.exception.DomainException;

public class HasNoAccessToSpaceException extends BadRequestDomainException {

    public HasNoAccessToSpaceException() {
        super("HasNoAccessToSpaceException");
    }

    public HasNoAccessToSpaceException(String id) {
        super("HasNoAccessToSpaceException");
    }
}
