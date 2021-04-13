package com.sol.domain.userOnPlan.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class UserOnPlanNotFoundException extends DomainException {

    public UserOnPlanNotFoundException() {
        super("UserOnPlan not found");
    }

    public UserOnPlanNotFoundException(String id) {
        super("UserOnPlan not found by ID: " + id);
    }
}
