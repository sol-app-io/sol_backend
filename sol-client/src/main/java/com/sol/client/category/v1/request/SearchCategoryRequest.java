package com.sol.client.category.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import io.swagger.annotations.ApiModel;
import com.sol.domain.category.port.filters.CategoryFilters;


@ApiModel("Место покупателя: для запроса на поиск")
public class SearchCategoryRequest extends SearchApiRequest {
    public CategoryFilters toFilters() {
        return CategoryFilters.builder()
                .limit(super.getLimit())
                .offset(super.getOffset())
                .query(super.getQuery())
                .sortDirection(super.getSortDirection())
                .sortName(super.getSortName())
                .build();
    }
}
