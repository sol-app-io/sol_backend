package com.sol.infrastructure.database.mongo.backgroundTaskForView.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.backgroundTaskForView.port.filters.BackgroundTaskForViewFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final BackgroundTaskForViewFilters backgroundTaskForViewFilters;

    public FindWithFiltersQuery(BackgroundTaskForViewFilters backgroundTaskForViewFilters) {
        super(backgroundTaskForViewFilters);
        this.backgroundTaskForViewFilters = backgroundTaskForViewFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
