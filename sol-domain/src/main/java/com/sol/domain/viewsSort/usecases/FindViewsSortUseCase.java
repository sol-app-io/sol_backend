package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.port.filters.ViewsSortFilters;

/**
 * Поиск по коллекции
 */
public class FindViewsSortUseCase extends AbstractFindWithFiltersUseCase<ViewsSortEntity, ViewsSortFilters, ViewsSortRepository> {

    public FindViewsSortUseCase(ViewsSortRepository repository) {
        super(repository);
    }
}