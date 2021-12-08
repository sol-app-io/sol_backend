package com.sol.infrastructure.database.mongo.viewUser.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.sol.domain.viewUser.port.filters.ViewUserFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final ViewUserFilters viewUserEntityFilters;

    public FindWithFiltersQuery(ViewUserFilters viewUserEntityFilters) {
        super(viewUserEntityFilters);
        this.viewUserEntityFilters = viewUserEntityFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
