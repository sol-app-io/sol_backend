package com.sol.infrastructure.database.mongo.view.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.view.port.filters.ViewFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final ViewFilters viewFilters;

    public FindWithFiltersQuery(ViewFilters viewFilters) {
        super(viewFilters);
        this.viewFilters = viewFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
