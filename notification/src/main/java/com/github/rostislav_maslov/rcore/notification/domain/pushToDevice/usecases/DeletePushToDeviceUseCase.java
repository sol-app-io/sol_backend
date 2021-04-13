package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceRepository;

/**
 * Удаление сущности
 */
public class DeletePushToDeviceUseCase extends AbstractDeleteUseCase<String, PushToDeviceRepository> {

    public DeletePushToDeviceUseCase(PushToDeviceRepository repository) {
        super(repository);
    }
}
