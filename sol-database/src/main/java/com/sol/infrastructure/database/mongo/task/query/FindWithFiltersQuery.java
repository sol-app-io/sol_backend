package com.sol.infrastructure.database.mongo.task.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.task.port.filters.TaskFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final TaskFilters taskFilters;

    public FindWithFiltersQuery(TaskFilters taskFilters) {
        super(taskFilters);
        this.taskFilters = taskFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
