package com.sol.domain.taskInView.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class TaskInViewNotFoundException extends NotFoundDomainException {

    public TaskInViewNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public TaskInViewNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
