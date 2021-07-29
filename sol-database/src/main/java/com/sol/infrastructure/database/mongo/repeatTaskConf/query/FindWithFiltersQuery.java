package com.sol.infrastructure.database.mongo.repeatTaskConf.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.repeatTaskConf.port.filters.RepeatTaskConfFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final RepeatTaskConfFilters repeatTaskConfFilters;

    public FindWithFiltersQuery(RepeatTaskConfFilters repeatTaskConfFilters) {
        super(repeatTaskConfFilters);
        this.repeatTaskConfFilters = repeatTaskConfFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
