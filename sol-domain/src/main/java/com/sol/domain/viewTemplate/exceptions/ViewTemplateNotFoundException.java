package com.sol.domain.viewTemplate.exceptions;

import com.rcore.domain.commons.exception.NotFoundDomainException;

public class ViewTemplateNotFoundException extends NotFoundDomainException {

    public ViewTemplateNotFoundException() {
        super(
                "Domain. not found"
        );
    }

    public ViewTemplateNotFoundException(String id) {
        super(
                "Domain. not found by ID: " + id
        );
    }
}
