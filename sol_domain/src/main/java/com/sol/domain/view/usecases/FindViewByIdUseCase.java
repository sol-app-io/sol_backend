package com.sol.domain.view.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.view.entity.ViewEntity;
import com.sol.domain.view.port.ViewRepository;

/**
 * Поиск сущности
 */
public class FindViewByIdUseCase extends AbstractFindByIdUseCase<String, ViewEntity, ViewRepository> {

    public FindViewByIdUseCase(ViewRepository repository) {
        super(repository);
    }
}