package com.sol.domain.backgroundTaskForView.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.backgroundTaskForView.usecases.DeleteBackgroundTaskForViewUseCase;
import com.sol.domain.backgroundTaskForView.usecases.FindBackgroundTaskForViewByIdUseCase;
import com.sol.domain.backgroundTaskForView.usecases.UpdateBackgroundTaskForViewUseCase;
import com.sol.domain.backgroundTaskForView.usecases.FindBackgroundTaskForViewUseCase;

@Accessors(fluent = true)
@Getter
public class BackgroundTaskForViewConfig {
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;
    private final DeleteBackgroundTaskForViewUseCase deleteBackgroundTaskForViewUseCase;
    private final FindBackgroundTaskForViewByIdUseCase findBackgroundTaskForViewByIdUseCase;
    private final UpdateBackgroundTaskForViewUseCase updateBackgroundTaskForViewUseCase;
    private final FindBackgroundTaskForViewUseCase findBackgroundTaskForViewUseCase;

    public BackgroundTaskForViewConfig(BackgroundTaskForViewRepository backgroundTaskForViewRepository, BackgroundTaskForViewIdGenerator<?> backgroundTaskForViewIdGenerator) {
        this.createBackgroundTaskForViewUseCase = new CreateBackgroundTaskForViewUseCase(backgroundTaskForViewRepository, backgroundTaskForViewIdGenerator);
        this.deleteBackgroundTaskForViewUseCase = new DeleteBackgroundTaskForViewUseCase(backgroundTaskForViewRepository);
        this.findBackgroundTaskForViewByIdUseCase = new FindBackgroundTaskForViewByIdUseCase(backgroundTaskForViewRepository);
        this.updateBackgroundTaskForViewUseCase = new UpdateBackgroundTaskForViewUseCase(backgroundTaskForViewRepository);
        this.findBackgroundTaskForViewUseCase = new FindBackgroundTaskForViewUseCase(backgroundTaskForViewRepository);
    }
}
