package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.entity.PushFeedEntity;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedRepository;

/**
 * Поиск сущности
 */
public class FindPushFeedByIdUseCase extends AbstractFindByIdUseCase<String, PushFeedEntity, PushFeedRepository> {

    public FindPushFeedByIdUseCase(PushFeedRepository repository) {
        super(repository);
    }
}