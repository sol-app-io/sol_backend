package com.sol.domain.category.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.category.port.CategoryIdGenerator;
import com.sol.domain.category.port.CategoryRepository;
import com.sol.domain.category.usecases.CreateCategoryUseCase;
import com.sol.domain.category.usecases.DeleteCategoryUseCase;
import com.sol.domain.category.usecases.FindCategoryByIdUseCase;
import com.sol.domain.category.usecases.UpdateCategoryUseCase;
import com.sol.domain.category.usecases.FindCategoryUseCase;

@Accessors(fluent = true)
@Getter
public class CategoryConfig {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final FindCategoryUseCase findCategoryUseCase;

    public CategoryConfig(CategoryRepository categoryRepository, CategoryIdGenerator<?> categoryIdGenerator) {
        this.createCategoryUseCase = new CreateCategoryUseCase(categoryRepository, categoryIdGenerator);
        this.deleteCategoryUseCase = new DeleteCategoryUseCase(categoryRepository);
        this.findCategoryByIdUseCase = new FindCategoryByIdUseCase(categoryRepository);
        this.updateCategoryUseCase = new UpdateCategoryUseCase(categoryRepository);
        this.findCategoryUseCase = new FindCategoryUseCase(categoryRepository);
    }
}
