package com.sol.domain.category.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class CategoryNotFoundException extends NotFoundDomainException {

    public CategoryNotFoundException() {
        super(
                "Domain not found"
        );
    }

    public CategoryNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
