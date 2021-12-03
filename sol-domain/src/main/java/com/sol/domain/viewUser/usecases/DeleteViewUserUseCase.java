package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.viewUser.port.ViewUserRepository;

/**
 * Удаление сущности
 */
public class DeleteViewUserUseCase extends AbstractDeleteUseCase<String, ViewUserRepository> {

    public DeleteViewUserUseCase(ViewUserRepository repository) {
        super(repository);
    }
}
