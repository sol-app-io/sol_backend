package com.sol.domain.view.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.view.entity.ViewType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.view.entity.ViewEntity;
import com.sol.domain.view.exceptions.ViewNotFoundException;
import com.sol.domain.view.port.ViewRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewUseCase extends UseCase<UpdateViewUseCase.InputValues, SingletonEntityOutputValues<ViewEntity>> {

    private final ViewRepository viewRepository;

    @Override
    public SingletonEntityOutputValues<ViewEntity> execute(InputValues inputValues) {

        ViewEntity viewEntity = viewRepository.findById(inputValues.getId())
                .orElseThrow(ViewNotFoundException::new);

        // Тут изменение данных
        viewEntity.setOwnerId(inputValues.ownerId);
        viewEntity.setSpaceId(inputValues.spaceId);
        viewEntity.setTitle(inputValues.title);
        viewEntity.setIcon(inputValues.icon);
        viewEntity.setSpaceId(inputValues.spaceId);
        viewEntity.setType(inputValues.type);
        
        viewEntity = viewRepository.save(viewEntity);

        return SingletonEntityOutputValues.of(viewEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String ownerId;
        protected String spaceId;
        protected String title;
        protected Icon icon;
        protected ViewType type;
    }

}
