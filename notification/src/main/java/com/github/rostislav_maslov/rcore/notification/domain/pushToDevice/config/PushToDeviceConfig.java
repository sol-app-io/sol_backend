package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceRepository;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases.CreatePushToDeviceUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases.DeletePushToDeviceUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases.FindPushToDeviceByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases.UpdatePushToDeviceUseCase;

@Accessors(fluent = true)
@Getter
public class PushToDeviceConfig {
    private final CreatePushToDeviceUseCase createPushToDeviceUseCase;
    private final DeletePushToDeviceUseCase deletePushToDeviceUseCase;
    private final FindPushToDeviceByIdUseCase findPushToDeviceByIdUseCase;
    private final UpdatePushToDeviceUseCase updatePushToDeviceUseCase;

    public PushToDeviceConfig(PushToDeviceRepository pushToDeviceRepository, PushToDeviceIdGenerator<?> pushToDeviceIdGenerator) {
        this.createPushToDeviceUseCase = new CreatePushToDeviceUseCase(pushToDeviceRepository, pushToDeviceIdGenerator);
        this.deletePushToDeviceUseCase = new DeletePushToDeviceUseCase(pushToDeviceRepository);
        this.findPushToDeviceByIdUseCase = new FindPushToDeviceByIdUseCase(pushToDeviceRepository);
        this.updatePushToDeviceUseCase = new UpdatePushToDeviceUseCase(pushToDeviceRepository);
    }
}
