package com.sol.domain.view.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.view.entity.*;
import com.sol.domain.view.port.ViewIdGenerator;
import com.sol.domain.view.port.ViewRepository;

/**
 * Создание сущности
 */
public class CreateViewUseCase extends AbstractCreateUseCase<ViewEntity, ViewIdGenerator, ViewRepository, CreateViewUseCase.InputValues> {


    public CreateViewUseCase(ViewRepository repository, ViewIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewEntity> execute(InputValues inputValues) {

        validate(inputValues);

        ViewEntity viewEntity = new ViewEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        viewEntity.setOwnerId(inputValues.ownerId);
        viewEntity.setSpaceId(inputValues.spaceId);
        viewEntity.setTitle(inputValues.title);
        viewEntity.setIcon(inputValues.icon);
        viewEntity.setSpaceId(inputValues.spaceId);
        viewEntity.setType(inputValues.type);

        viewEntity = repository.save(viewEntity);

        return SingletonEntityOutputValues.of(viewEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected String spaceId;
        protected String title;
        protected Icon icon;
        protected String spaceId;
        protected ViewType type;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
