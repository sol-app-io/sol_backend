package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PushToDeviceNotFoundException extends DomainException {

    public PushToDeviceNotFoundException() {
        super("PushToDevice not found");
    }

    public PushToDeviceNotFoundException(String id) {
        super("PushToDevice not found by ID: " + id);
    }
}
