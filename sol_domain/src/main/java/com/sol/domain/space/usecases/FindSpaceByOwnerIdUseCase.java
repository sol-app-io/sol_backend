package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.port.SpaceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Поиск сущности
 */
@RequiredArgsConstructor
public class FindSpaceByOwnerIdUseCase extends UseCase<FindSpaceByOwnerIdUseCase.InputValues, SingletonEntityOutputValues<List<SpaceEntity>>> {

    private final SpaceRepository spaceRepository;

    @Override
    public SingletonEntityOutputValues<List<SpaceEntity>> execute(FindSpaceByOwnerIdUseCase.InputValues inputValues) {
        List<SpaceEntity> spaceEntities = spaceRepository.findByOwner(inputValues.getOwnerId());
        return SingletonEntityOutputValues.of(spaceEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        protected String ownerId;
    }

}
