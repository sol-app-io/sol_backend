package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserFilters;

/**
 * Поиск по коллекции
 */
public class FindViewUserUseCase extends AbstractFindWithFiltersUseCase<ViewUserEntity, ViewUserFilters, ViewUserRepository> {

    public FindViewUserUseCase(ViewUserRepository repository) {
        super(repository);
    }
}