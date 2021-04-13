package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfRepository;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases.CreatePushNotificationConfUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases.DeletePushNotificationConfUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases.FindPushNotificationConfByIdUseCase;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases.UpdatePushNotificationConfUseCase;

@Accessors(fluent = true)
@Getter
public class PushNotificationConfConfig {
    private final CreatePushNotificationConfUseCase createPushNotificationConfUseCase;
    private final DeletePushNotificationConfUseCase deletePushNotificationConfUseCase;
    private final FindPushNotificationConfByIdUseCase findPushNotificationConfByIdUseCase;
    private final UpdatePushNotificationConfUseCase updatePushNotificationConfUseCase;

    public PushNotificationConfConfig(PushNotificationConfRepository pushNotificationConfRepository, PushNotificationConfIdGenerator<?> pushNotificationConfIdGenerator) {
        this.createPushNotificationConfUseCase = new CreatePushNotificationConfUseCase(pushNotificationConfRepository, pushNotificationConfIdGenerator);
        this.deletePushNotificationConfUseCase = new DeletePushNotificationConfUseCase(pushNotificationConfRepository);
        this.findPushNotificationConfByIdUseCase = new FindPushNotificationConfByIdUseCase(pushNotificationConfRepository);
        this.updatePushNotificationConfUseCase = new UpdatePushNotificationConfUseCase(pushNotificationConfRepository);
    }
}
