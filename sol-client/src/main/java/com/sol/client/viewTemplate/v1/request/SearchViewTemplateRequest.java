package com.sol.client.viewTemplate.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;


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
