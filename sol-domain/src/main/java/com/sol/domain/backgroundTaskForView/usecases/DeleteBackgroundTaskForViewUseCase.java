package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;

/**
 * Удаление сущности
 */
public class DeleteBackgroundTaskForViewUseCase extends AbstractDeleteUseCase<String, BackgroundTaskForViewRepository> {

    public DeleteBackgroundTaskForViewUseCase(BackgroundTaskForViewRepository repository) {
        super(repository);
    }
}
