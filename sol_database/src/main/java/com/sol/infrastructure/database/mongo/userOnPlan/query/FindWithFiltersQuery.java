package com.sol.infrastructure.database.mongo.userOnPlan.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.userOnPlan.port.filters.UserOnPlanFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final UserOnPlanFilters userOnPlanFilters;

    public FindWithFiltersQuery(UserOnPlanFilters userOnPlanFilters) {
        super(userOnPlanFilters);
        this.userOnPlanFilters = userOnPlanFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
