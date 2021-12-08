package com.sol.domain.category.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.exceptions.CategoryNotFoundException;
import com.sol.domain.category.port.CategoryRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateCategoryUseCase extends UseCase<UpdateCategoryUseCase.InputValues, SingletonEntityOutputValues<CategoryEntity>> {

    private final CategoryRepository categoryRepository;

    @Override
    public SingletonEntityOutputValues<CategoryEntity> execute(InputValues inputValues) {
        CategoryEntity categoryEntity = categoryRepository.findById(inputValues.getId())
                .orElseThrow(CategoryNotFoundException::new);

        categoryEntity.setTitle(inputValues.title);
        categoryEntity.setDescription(inputValues.description);
        categoryEntity.setParentId(inputValues.parentId);
        
        categoryEntity = categoryRepository.save(categoryEntity);

        return SingletonEntityOutputValues.of(categoryEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
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
