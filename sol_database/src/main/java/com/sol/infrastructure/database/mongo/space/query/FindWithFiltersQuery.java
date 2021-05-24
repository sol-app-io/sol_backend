package com.sol.infrastructure.database.mongo.space.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.space.port.filters.SpaceFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final SpaceFilters spaceFilters;

    public FindWithFiltersQuery(SpaceFilters spaceFilters) {
        super(spaceFilters);
        this.spaceFilters = spaceFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
