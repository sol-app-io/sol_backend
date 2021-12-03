package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;

/**
 * Поиск сущности
 */
public class FindBackgroundTaskForViewByIdUseCase extends AbstractFindByIdUseCase<String, BackgroundTaskForViewEntity, BackgroundTaskForViewRepository> {

    public FindBackgroundTaskForViewByIdUseCase(BackgroundTaskForViewRepository repository) {
        super(repository);
    }
}