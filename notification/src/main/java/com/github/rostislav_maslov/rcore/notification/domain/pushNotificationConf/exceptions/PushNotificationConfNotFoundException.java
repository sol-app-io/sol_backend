package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PushNotificationConfNotFoundException extends DomainException {

    public PushNotificationConfNotFoundException() {
        super("PushNotificationConf not found");
    }

    public PushNotificationConfNotFoundException(String id) {
        super("PushNotificationConf not found by ID: " + id);
    }
}
