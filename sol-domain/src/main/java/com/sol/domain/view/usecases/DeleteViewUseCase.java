package com.sol.domain.view.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.view.port.ViewRepository;

/**
 * Удаление сущности
 */
public class DeleteViewUseCase extends AbstractDeleteUseCase<String, ViewRepository> {

    public DeleteViewUseCase(ViewRepository repository) {
        super(repository);
    }
}
