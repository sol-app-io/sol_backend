package com.sol.client.category.v1;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sol.client.category.v1.request.CreateCategoryRequest;
import com.sol.client.category.v1.request.UpdateCategoryRequest;
import com.sol.client.category.v1.request.SearchCategoryRequest;
import com.sol.client.category.v1.response.CategoryResponse;

@Api(tags = "Category API", description = "Category docs")
@RestController
public interface CategoryResource {

    @ApiOperation("Создание нового документа в ресурсе")
    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse> create(@RequestBody CreateCategoryRequest request);

    @ApiOperation("Обновление документа в ресурсе")
    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse> update(@PathVariable("id") String id, @RequestBody UpdateCategoryRequest request);

    @ApiOperation("Удаление документа в ресурсе")
    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    OkApiResponse delete(@PathVariable("id") String id);

    @ApiOperation("Получение документа в ресурсе")
    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<CategoryResponse>> collection(SearchCategoryRequest request);

    @ApiOperation("Получение продукта по ID")
    @GetMapping(value =  Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse>  singleton(@PathVariable("id") String id);
}
