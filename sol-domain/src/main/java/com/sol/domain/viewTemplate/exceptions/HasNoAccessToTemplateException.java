package com.sol.domain.viewTemplate.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class HasNoAccessToTemplateException extends DomainException {

    public HasNoAccessToTemplateException() {
        super(
                "Has no access to template"
        );
    }

    public HasNoAccessToTemplateException(String id) {
        super(
                "Has no access to template "  + id
        );
    }

}
