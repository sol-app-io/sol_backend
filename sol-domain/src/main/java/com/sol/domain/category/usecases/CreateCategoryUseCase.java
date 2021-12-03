package com.sol.domain.category.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.category.entity.*;
import com.sol.domain.category.port.CategoryIdGenerator;
import com.sol.domain.category.port.CategoryRepository;

/**
 * Создание сущности
 */
public class CreateCategoryUseCase extends AbstractCreateUseCase<CategoryEntity, CategoryIdGenerator<?>, CategoryRepository, CreateCategoryUseCase.InputValues> {


    public CreateCategoryUseCase(CategoryRepository repository, CategoryIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<CategoryEntity> execute(InputValues inputValues) {

        CategoryEntity categoryEntity = new CategoryEntity(idGenerator.generate());

        categoryEntity.setTitle(inputValues.title);
        categoryEntity.setDescription(inputValues.description);
        categoryEntity.setParentId(inputValues.parentId);

        categoryEntity = repository.save(categoryEntity);

        return SingletonEntityOutputValues.of(categoryEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Title 
        */
        protected String title;
        /**
        * description 
        */
        protected String description;
        /**
        * Parent Category 
        */
        protected String parentId;

    }
}
