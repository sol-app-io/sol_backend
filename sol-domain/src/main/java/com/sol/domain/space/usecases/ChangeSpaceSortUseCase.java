package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.port.SpaceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class ChangeSpaceSortUseCase extends UseCase<ChangeSpaceSortUseCase.InputValues, SingletonEntityOutputValues<List<SpaceEntity>>> {

    private final SpaceRepository spaceRepository;

    @Override
    public SingletonEntityOutputValues<List<SpaceEntity>> execute(InputValues inputValues) {
        List<SpaceEntity> spaceEntities = new ArrayList<>();
        for (String spaceId : inputValues.spaceIds) {
            SpaceEntity spaceEntity = spaceRepository
                    .findById(spaceId)
                    .orElseThrow(SpaceNotFoundException::new);
            if (spaceEntity.checkAccess(inputValues.currentUserId) == false) {
                throw new HasNoAccessToSpaceException();
            }
            spaceEntities.add(spaceEntity);
        }

        for (int index = 0; index < spaceEntities.size(); index++) {
            SpaceEntity spaceEntity = spaceEntities.get(index);
            spaceEntity.setSortNum(index);
            spaceEntity = spaceRepository.save(spaceEntity);
        }

        return SingletonEntityOutputValues.of(spaceEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        protected List<String> spaceIds;
        protected String currentUserId;
    }

}
