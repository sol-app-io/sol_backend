package com.sol.domain.backgroundTaskForView.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class BackgroundTaskForViewNotFoundException extends NotFoundDomainException {

    public BackgroundTaskForViewNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public BackgroundTaskForViewNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
