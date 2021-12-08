package com.sol.domain.category.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.category.port.CategoryRepository;

/**
 * Удаление сущности
 */
public class DeleteCategoryUseCase extends AbstractDeleteUseCase<String, CategoryRepository> {

    public DeleteCategoryUseCase(CategoryRepository repository) {
        super(repository);
    }
}
