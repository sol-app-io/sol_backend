package com.sol.infrastructure.database.mongo.viewsSort.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.viewsSort.port.filters.ViewsSortFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final ViewsSortFilters viewsSortFilters;

    public FindWithFiltersQuery(ViewsSortFilters viewsSortFilters) {
        super(viewsSortFilters);
        this.viewsSortFilters = viewsSortFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
