package com.sol.infrastructure.database.mongo.solUser.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.solUser.port.filters.SolUserFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final SolUserFilters solUserFilters;

    public FindWithFiltersQuery(SolUserFilters solUserFilters) {
        super(solUserFilters);
        this.solUserFilters = solUserFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
