package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.SpaceRepository;

/**
 * Поиск сущности
 */
public class FindSpaceByIdUseCase extends AbstractFindByIdUseCase<String, SpaceEntity, SpaceRepository> {

    public FindSpaceByIdUseCase(SpaceRepository repository) {
        super(repository);
    }
}