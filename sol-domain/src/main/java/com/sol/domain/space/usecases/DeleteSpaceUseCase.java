package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.space.port.SpaceRepository;

/**
 * Удаление сущности
 */
public class DeleteSpaceUseCase extends AbstractDeleteUseCase<String, SpaceRepository> {

    public DeleteSpaceUseCase(SpaceRepository repository) {
        super(repository);
    }
}
