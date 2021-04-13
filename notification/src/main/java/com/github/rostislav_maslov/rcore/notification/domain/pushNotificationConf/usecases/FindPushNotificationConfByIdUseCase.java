package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.entity.PushNotificationConfEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfRepository;

/**
 * Поиск сущности
 */
public class FindPushNotificationConfByIdUseCase extends AbstractFindByIdUseCase<String, PushNotificationConfEntity, PushNotificationConfRepository> {

    public FindPushNotificationConfByIdUseCase(PushNotificationConfRepository repository) {
        super(repository);
    }
}