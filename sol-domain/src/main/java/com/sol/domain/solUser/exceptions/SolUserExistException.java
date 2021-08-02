package com.sol.domain.solUser.exceptions;

import com.rcore.domain.commons.exception.BadRequestDomainException;

import java.util.Arrays;
import java.util.List;

public class SolUserExistException extends BadRequestDomainException {
    private static List<Error> errors = Arrays.asList(new Error(
            "solUser",
            "SolUserExistException",
            "solUser.exception.userExist"
    ));

    public SolUserExistException() {
        super("Sorry, but this email already use in Sol.App. Try login or use another email", errors);
    }
}
