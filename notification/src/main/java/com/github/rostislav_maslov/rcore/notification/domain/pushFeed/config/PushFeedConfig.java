package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedIdGenerator;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedRepository;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases.CreatePushFeedUseCase;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases.DeletePushFeedUseCase;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases.FindPushFeedByIdUseCase;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases.UpdatePushFeedUseCase;

@Accessors(fluent = true)
@Getter
public class PushFeedConfig {
    private final CreatePushFeedUseCase createPushFeedUseCase;
    private final DeletePushFeedUseCase deletePushFeedUseCase;
    private final FindPushFeedByIdUseCase findPushFeedByIdUseCase;
    private final UpdatePushFeedUseCase updatePushFeedUseCase;

    public PushFeedConfig(PushFeedRepository pushFeedRepository, PushFeedIdGenerator<?> pushFeedIdGenerator) {
        this.createPushFeedUseCase = new CreatePushFeedUseCase(pushFeedRepository, pushFeedIdGenerator);
        this.deletePushFeedUseCase = new DeletePushFeedUseCase(pushFeedRepository);
        this.findPushFeedByIdUseCase = new FindPushFeedByIdUseCase(pushFeedRepository);
        this.updatePushFeedUseCase = new UpdatePushFeedUseCase(pushFeedRepository);
    }
}
