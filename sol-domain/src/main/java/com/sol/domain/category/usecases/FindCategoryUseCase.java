package com.sol.domain.category.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.port.CategoryRepository;
import com.sol.domain.category.port.filters.CategoryFilters;

/**
 * Поиск по коллекции
 */
public class FindCategoryUseCase extends AbstractFindWithFiltersUseCase<CategoryEntity, CategoryFilters, CategoryRepository> {

    public FindCategoryUseCase(CategoryRepository repository) {
        super(repository);
    }
}