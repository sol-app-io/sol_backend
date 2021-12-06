package com.sol.infrastructure.database.mongo.taskInView.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.taskInView.port.filters.TaskInViewFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final TaskInViewFilters taskInViewFilters;

    public FindWithFiltersQuery(TaskInViewFilters taskInViewFilters) {
        super(taskInViewFilters);
        this.taskInViewFilters = taskInViewFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
