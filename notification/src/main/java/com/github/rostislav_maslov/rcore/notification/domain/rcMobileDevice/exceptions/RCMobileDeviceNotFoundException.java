package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class RCMobileDeviceNotFoundException extends DomainException {

    public RCMobileDeviceNotFoundException() {
        super("RCMobileDevice not found");
    }

    public RCMobileDeviceNotFoundException(String id) {
        super("RCMobileDevice not found by ID: " + id);
    }
}
