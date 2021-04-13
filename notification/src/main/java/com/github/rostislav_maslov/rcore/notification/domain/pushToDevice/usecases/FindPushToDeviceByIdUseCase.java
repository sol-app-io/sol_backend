package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.entity.PushToDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceRepository;

/**
 * Поиск сущности
 */
public class FindPushToDeviceByIdUseCase extends AbstractFindByIdUseCase<String, PushToDeviceEntity, PushToDeviceRepository> {

    public FindPushToDeviceByIdUseCase(PushToDeviceRepository repository) {
        super(repository);
    }
}