package com.sol.domain.task.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class TaskNotFoundException extends NotFoundDomainException {

    public TaskNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public TaskNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
