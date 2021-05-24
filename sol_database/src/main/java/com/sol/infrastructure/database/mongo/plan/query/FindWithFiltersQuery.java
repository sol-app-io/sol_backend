package com.sol.infrastructure.database.mongo.plan.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.plan.port.filters.PlanFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final PlanFilters planFilters;

    public FindWithFiltersQuery(PlanFilters planFilters) {
        super(planFilters);
        this.planFilters = planFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
