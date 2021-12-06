package com.sol.client.viewTemplate.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import io.swagger.annotations.ApiModel;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;


@ApiModel("View Template: для запроса на поиск")
public class SearchViewTemplateRequest extends SearchApiRequest {
    public ViewTemplateFilters toFilters() {
        return ViewTemplateFilters.builder()
                .limit(super.getLimit())
                .offset(super.getOffset())
                .query(super.getQuery())
                .sortDirection(super.getSortDirection())
                .sortName(super.getSortName())
                .build();
    }
}
