package com.sol.domain.viewsSort.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class ViewsSortNotFoundException extends NotFoundDomainException {

    public ViewsSortNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public ViewsSortNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
