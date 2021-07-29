package com.sol.client.base;

import com.sol.client.base.api.Error;
import com.sol.client.base.api.ErrorApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.foodtechlab.lib.auth.integration.core.authorization.exception.InvalidTokenException;
import ru.foodtechlab.lib.auth.service.domain.authorization.exceptions.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorsController {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorApiResponse<Error>> databaseError(Exception e, HttpServletRequest request, Locale locale) {

        ErrorApiResponse<Error> errorApiResponse = ErrorApiResponse.auth(
                Arrays.asList(Error.unknownError("InvalidTokenException", "", e)
                ), request.getRequestURI());
        log.error("Unknown exception", errorApiResponse.toString());
        return ResponseEntity.status(errorApiResponse.getStatus())
                .body(errorApiResponse);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorApiResponse<Error>> badCredentialsException(Exception e, HttpServletRequest request, Locale locale) {

        ErrorApiResponse<Error> errorApiResponse = ErrorApiResponse.auth(
                Arrays.asList(Error.unknownError("BadCredentialsException", "", e)
                ), request.getRequestURI());
        log.error("Unknown exception", errorApiResponse.toString());
        return ResponseEntity.status(errorApiResponse.getStatus())
                .body(errorApiResponse);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(com.sol.domain.solUser.exceptions.BadCredentialsException.class)
    public ResponseEntity<ErrorApiResponse<Error>> badCredentialsExceptionSol(Exception e, HttpServletRequest request, Locale locale) {

        ErrorApiResponse<Error> errorApiResponse = ErrorApiResponse.auth(
                Arrays.asList(Error.unknownError("BadCredentialsException", "", e)
                ), request.getRequestURI());
        log.error("Unknown exception", errorApiResponse.toString());
        return ResponseEntity.status(errorApiResponse.getStatus())
                .body(errorApiResponse);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse<Error>> handleUnknownException(Exception e, HttpServletRequest request, Locale locale) {
        ErrorApiResponse<Error> errorApiResponse = ErrorApiResponse.internalServerError(
                Arrays.asList(Error.unknownError("INTERNAL_SERVER_ERROR", "", e)
                ), request.getRequestURI());
        log.error("Unknown exception", errorApiResponse.toString());
        return ResponseEntity.status(errorApiResponse.getStatus())
                .body(errorApiResponse);
    }

}
