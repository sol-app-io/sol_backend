package com.sol.domain.solUser.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.solUser.port.SolUserRepository;

/**
 * Удаление сущности
 */
public class DeleteSolUserUseCase extends AbstractDeleteUseCase<String, SolUserRepository> {

    public DeleteSolUserUseCase(SolUserRepository repository) {
        super(repository);
    }
}
