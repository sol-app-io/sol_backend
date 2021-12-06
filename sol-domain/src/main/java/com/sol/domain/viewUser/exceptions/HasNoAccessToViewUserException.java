package com.sol.domain.viewUser.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class HasNoAccessToViewUserException extends NotFoundDomainException {

    public HasNoAccessToViewUserException() {
        super(
                "Has no access to view"
        );
    }
}
