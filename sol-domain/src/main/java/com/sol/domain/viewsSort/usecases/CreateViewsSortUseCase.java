package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.viewsSort.entity.*;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Создание сущности
 */
public class CreateViewsSortUseCase extends AbstractCreateUseCase<ViewsSortEntity, ViewsSortIdGenerator<?>, ViewsSortRepository, CreateViewsSortUseCase.InputValues> {


    public CreateViewsSortUseCase(ViewsSortRepository repository, ViewsSortIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewsSortEntity> execute(InputValues inputValues) {

        ViewsSortEntity viewsSortEntity = new ViewsSortEntity(idGenerator.generate());

        viewsSortEntity.setOwnerId(inputValues.ownerId);
        viewsSortEntity.setType(inputValues.type);

        viewsSortEntity = repository.save(viewsSortEntity);

        return SingletonEntityOutputValues.of(viewsSortEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Owner 
        */
        protected String ownerId;
        /**
        * Type 
        */
        protected String type;
        /**
        * Views 
        */
        protected List<String> viewsId = new ArrayList<>();

    }
}
