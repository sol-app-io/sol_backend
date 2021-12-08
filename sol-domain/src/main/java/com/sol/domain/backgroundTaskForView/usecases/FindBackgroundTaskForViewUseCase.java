package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.backgroundTaskForView.port.filters.BackgroundTaskForViewFilters;

/**
 * Поиск по коллекции
 */
public class FindBackgroundTaskForViewUseCase extends AbstractFindWithFiltersUseCase<BackgroundTaskForViewEntity, BackgroundTaskForViewFilters, BackgroundTaskForViewRepository> {

    public FindBackgroundTaskForViewUseCase(BackgroundTaskForViewRepository repository) {
        super(repository);
    }
}