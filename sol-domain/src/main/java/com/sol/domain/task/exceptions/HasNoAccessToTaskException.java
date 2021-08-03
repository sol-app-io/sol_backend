package com.sol.domain.task.exceptions;

import com.rcore.domain.commons.exception.BadRequestDomainException;
import com.rcore.domain.commons.exception.NotFoundDomainException;

public class HasNoAccessToTaskException extends BadRequestDomainException {

    public HasNoAccessToTaskException() {
        super(
                "HasNoAccessToTaskException"
        );
    }

    public HasNoAccessToTaskException(String id) {
        super(
                "HasNoAccessToTaskException"
        );
    }
}
