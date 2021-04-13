package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceRepository;

/**
 * Удаление сущности
 */
public class DeleteRCMobileDeviceUseCase extends AbstractDeleteUseCase<String, RCMobileDeviceRepository> {

    public DeleteRCMobileDeviceUseCase(RCMobileDeviceRepository repository) {
        super(repository);
    }
}
