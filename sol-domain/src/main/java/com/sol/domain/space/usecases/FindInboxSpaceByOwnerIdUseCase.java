package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.SpaceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Поиск сущности
 */
@RequiredArgsConstructor
public class FindInboxSpaceByOwnerIdUseCase extends UseCase<FindInboxSpaceByOwnerIdUseCase.InputValues, SingletonEntityOutputValues<SpaceEntity>> {

    private final SpaceRepository spaceRepository;

    @Override
    public SingletonEntityOutputValues<SpaceEntity> execute(FindInboxSpaceByOwnerIdUseCase.InputValues inputValues) {
        List<SpaceEntity> spaceEntities = spaceRepository.findByOwner(inputValues.getOwnerId());
        for(SpaceEntity spaceEntity: spaceEntities){
            if(spaceEntity.getType().equals(SpaceEntity.Type.INBOX)) return SingletonEntityOutputValues.of(spaceEntity);
        }
        return SingletonEntityOutputValues.of(spaceEntities.get(0));
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        protected String ownerId;
    }

}
