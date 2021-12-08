package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;

/**
 * Поиск сущности
 */
public class FindViewUserByIdUseCase extends AbstractFindByIdUseCase<String, ViewUserEntity, ViewUserRepository> {

    public FindViewUserByIdUseCase(ViewUserRepository repository) {
        super(repository);
    }
}