package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.port.SpaceRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateSpaceUseCase extends UseCase<UpdateSpaceUseCase.InputValues, SingletonEntityOutputValues<SpaceEntity>> {

    private final SpaceRepository spaceRepository;

    @Override
    public SingletonEntityOutputValues<SpaceEntity> execute(InputValues inputValues) {

        SpaceEntity spaceEntity =
                spaceRepository
                        .findById(inputValues.getId())
                        .orElseThrow(SpaceNotFoundException::new);

        if(spaceEntity.checkAccess(inputValues.currentUserId) == false){
            throw new HasNoAccessToSpaceException();
        }

        // Тут изменение данных
        spaceEntity.setTitle(inputValues.title);
        spaceEntity.setIcon(inputValues.icon);
        spaceEntity = spaceRepository.save(spaceEntity);

        return SingletonEntityOutputValues.of(spaceEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String title;
        protected Icon icon;
        protected String currentUserId;
    }

}
