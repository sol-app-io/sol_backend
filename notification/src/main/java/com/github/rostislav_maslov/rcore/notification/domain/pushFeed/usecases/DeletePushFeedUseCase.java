package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedRepository;

/**
 * Удаление сущности
 */
public class DeletePushFeedUseCase extends AbstractDeleteUseCase<String, PushFeedRepository> {

    public DeletePushFeedUseCase(PushFeedRepository repository) {
        super(repository);
    }
}
