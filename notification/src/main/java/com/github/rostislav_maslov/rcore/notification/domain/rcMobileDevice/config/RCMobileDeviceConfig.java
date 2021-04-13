package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceRepository;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases.CreateRCMobileDeviceUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases.DeleteRCMobileDeviceUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases.FindRCMobileDeviceByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases.UpdateRCMobileDeviceUseCase;

@Accessors(fluent = true)
@Getter
public class RCMobileDeviceConfig {
    private final CreateRCMobileDeviceUseCase createRCMobileDeviceUseCase;
    private final DeleteRCMobileDeviceUseCase deleteRCMobileDeviceUseCase;
    private final FindRCMobileDeviceByIdUseCase findRCMobileDeviceByIdUseCase;
    private final UpdateRCMobileDeviceUseCase updateRCMobileDeviceUseCase;

    public RCMobileDeviceConfig(RCMobileDeviceRepository rcMobileDeviceRepository, RCMobileDeviceIdGenerator<?> rcMobileDeviceIdGenerator) {
        this.createRCMobileDeviceUseCase = new CreateRCMobileDeviceUseCase(rcMobileDeviceRepository, rcMobileDeviceIdGenerator);
        this.deleteRCMobileDeviceUseCase = new DeleteRCMobileDeviceUseCase(rcMobileDeviceRepository);
        this.findRCMobileDeviceByIdUseCase = new FindRCMobileDeviceByIdUseCase(rcMobileDeviceRepository);
        this.updateRCMobileDeviceUseCase = new UpdateRCMobileDeviceUseCase(rcMobileDeviceRepository);
    }
}
