package com.sol.domain.plan.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PlanNotFoundException extends DomainException {

    public PlanNotFoundException() {
        super("Plan not found");
    }

    public PlanNotFoundException(String id) {
        super("Plan not found by ID: " + id);
    }
}
