package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserRepository;

/**
 * Поиск сущности
 */
public class FindSolUserByIdUseCase extends AbstractFindByIdUseCase<String, SolUserEntity, SolUserRepository> {

    public FindSolUserByIdUseCase(SolUserRepository repository) {
        super(repository);
    }
}