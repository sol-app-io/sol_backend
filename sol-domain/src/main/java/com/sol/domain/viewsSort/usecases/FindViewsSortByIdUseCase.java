package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortRepository;

/**
 * Поиск сущности
 */
public class FindViewsSortByIdUseCase extends AbstractFindByIdUseCase<String, ViewsSortEntity, ViewsSortRepository> {

    public FindViewsSortByIdUseCase(ViewsSortRepository repository) {
        super(repository);
    }
}