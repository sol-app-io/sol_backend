package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.viewsSort.port.ViewsSortRepository;

/**
 * Удаление сущности
 */
public class DeleteViewsSortUseCase extends AbstractDeleteUseCase<String, ViewsSortRepository> {

    public DeleteViewsSortUseCase(ViewsSortRepository repository) {
        super(repository);
    }
}
