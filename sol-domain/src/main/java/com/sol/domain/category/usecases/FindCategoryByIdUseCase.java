package com.sol.domain.category.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.port.CategoryRepository;

/**
 * Поиск сущности
 */
public class FindCategoryByIdUseCase extends AbstractFindByIdUseCase<String, CategoryEntity, CategoryRepository> {

    public FindCategoryByIdUseCase(CategoryRepository repository) {
        super(repository);
    }
}