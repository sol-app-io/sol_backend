package com.sol.client.task.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import com.sol.domain.task.port.filters.TaskFilters;


public class SearchTaskRequest extends SearchApiRequest {
    public TaskFilters toFilters() {
        return TaskFilters.builder()
                .limit(super.getLimit())
                .offset(super.getOffset())
                .query(super.getQuery())
                .sortDirection(super.getSortDirection())
                .sortName(super.getSortName())
                .build();
    }
}
