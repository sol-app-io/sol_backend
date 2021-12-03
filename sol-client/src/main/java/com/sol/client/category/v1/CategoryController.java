package com.sol.client.category.v1;

import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.model.FiltersInputValues;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.sol.client.category.v1.request.CreateCategoryRequest;
import com.sol.client.category.v1.request.UpdateCategoryRequest;
import com.sol.client.category.v1.request.SearchCategoryRequest;
import com.sol.client.category.v1.mappers.CategoryResponseMapper;
import com.sol.client.category.v1.response.CategoryResponse;
import com.sol.domain.category.config.CategoryConfig;
import com.sol.domain.category.exceptions.CategoryNotFoundException;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component("categoryControllerV1")
public class CategoryController implements CategoryResource {
    private final UseCaseExecutor useCaseExecutor;
    private final CategoryConfig categoryConfig;


    @Override
    public SuccessApiResponse<CategoryResponse> create(CreateCategoryRequest request) {
        return useCaseExecutor.execute(
                categoryConfig.createCategoryUseCase(),
                request.toInputValues(),
                output -> SuccessApiResponse.of(CategoryResponseMapper.map(output.getEntity()))
        );
    }

    @Override
    public SuccessApiResponse<CategoryResponse> update(String id, UpdateCategoryRequest request) {
        return useCaseExecutor.execute(
                categoryConfig.updateCategoryUseCase(),
                request.toInputValues(id),
                output -> SuccessApiResponse.of(CategoryResponseMapper.map(output.getEntity()))
        );
    }

    @Override
    public OkApiResponse delete(String id) {
        return useCaseExecutor.execute(
                categoryConfig.deleteCategoryUseCase(),
                IdInputValues.of(id),
                o -> new OkApiResponse()
        );
    }

    @Override
    public SuccessApiResponse<SearchApiResponse<CategoryResponse>> collection(SearchCategoryRequest request) {
        return useCaseExecutor.execute(
                categoryConfig.findCategoryUseCase(),
                FiltersInputValues.of(request.toFilters()),
                (o) -> SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
                        o.getResult().getItems().stream().map(CategoryResponseMapper::map).collect(Collectors.toList()),
                        o.getResult().getCount()
                ))
        );
    }

    @Override
    public SuccessApiResponse<CategoryResponse> singleton(String id) {
        return useCaseExecutor.execute(
                categoryConfig.findCategoryByIdUseCase(),
                IdInputValues.of(id),
                o -> o.getEntity().map(entity -> SuccessApiResponse.of(CategoryResponseMapper.map(entity)))
                        .orElseThrow(CategoryNotFoundException::new)
        );
    }
}
