package com.sol.client.category.v1;

import com.rcore.rest.api.commons.response.OkApiResponse;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.sol.client.category.v1.request.CreateCategoryRequest;
import com.sol.client.category.v1.request.UpdateCategoryRequest;
import com.sol.client.category.v1.request.SearchCategoryRequest;
import com.sol.client.category.v1.response.CategoryResponse;

@RestController
public interface CategoryResource {

    @PostMapping(value = Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse> create(@RequestBody CreateCategoryRequest request);

    @PutMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse> update(@PathVariable("id") String id, @RequestBody UpdateCategoryRequest request);

    @DeleteMapping(value = Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    OkApiResponse delete(@PathVariable("id") String id);

    @GetMapping(value =  Routes.ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<SearchApiResponse<CategoryResponse>> collection(SearchCategoryRequest request);

    @GetMapping(value =  Routes.SINGLETON, produces = MediaType.APPLICATION_JSON_VALUE)
    SuccessApiResponse<CategoryResponse>  singleton(@PathVariable("id") String id);
}
