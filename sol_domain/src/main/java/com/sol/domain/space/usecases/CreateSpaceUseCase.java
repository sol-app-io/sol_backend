package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.space.entity.*;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.domain.space.port.SpaceRepository;

/**
 * Создание сущности
 */
public class CreateSpaceUseCase extends AbstractCreateUseCase<SpaceEntity, SpaceIdGenerator, SpaceRepository, CreateSpaceUseCase.InputValues> {


    public CreateSpaceUseCase(SpaceRepository repository, SpaceIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<SpaceEntity> execute(InputValues inputValues) {
        try {
            validate(inputValues);

            SpaceEntity spaceEntity = new SpaceEntity(idGenerator.generate());

            // Происходит заполнение всех полей
            spaceEntity.setTitle(inputValues.title);
            spaceEntity.setIcon(inputValues.icon);
            spaceEntity.setOwnerId(inputValues.ownerId);
            spaceEntity.setSortNum(repository.countSpaces(inputValues.getOwnerId()).intValue());
            spaceEntity.setType(inputValues.type);
            spaceEntity = repository.save(spaceEntity);

            return SingletonEntityOutputValues.of(spaceEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String title;
        protected Icon icon;
        protected String ownerId;
        protected SpaceEntity.Type type;
    }

    /**
     * Валидация входящий данных
     *
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
