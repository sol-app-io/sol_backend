package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfRepository;

/**
 * Удаление сущности
 */
public class DeletePushNotificationConfUseCase extends AbstractDeleteUseCase<String, PushNotificationConfRepository> {

    public DeletePushNotificationConfUseCase(PushNotificationConfRepository repository) {
        super(repository);
    }
}
