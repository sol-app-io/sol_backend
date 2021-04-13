package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.exceptions;

import com.rcore.domain.commons.exception.DomainException;

public class PushFeedNotFoundException extends DomainException {

    public PushFeedNotFoundException() {
        super("PushFeed not found");
    }

    public PushFeedNotFoundException(String id) {
        super("PushFeed not found by ID: " + id);
    }
}
