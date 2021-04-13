package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.entity.RCMobileDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceRepository;

/**
 * Поиск сущности
 */
public class FindRCMobileDeviceByIdUseCase extends AbstractFindByIdUseCase<String, RCMobileDeviceEntity, RCMobileDeviceRepository> {

    public FindRCMobileDeviceByIdUseCase(RCMobileDeviceRepository repository) {
        super(repository);
    }
}