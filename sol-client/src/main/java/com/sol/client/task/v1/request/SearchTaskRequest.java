package com.sol.client.task.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import io.swagger.annotations.ApiModel;
import com.sol.domain.task.port.filters.TaskFilters;


@ApiModel("Task: для запроса на поиск")
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
