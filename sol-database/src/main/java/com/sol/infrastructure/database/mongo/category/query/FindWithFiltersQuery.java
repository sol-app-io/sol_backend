package com.sol.infrastructure.database.mongo.category.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.category.port.filters.CategoryFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final CategoryFilters categoryFilters;

    public FindWithFiltersQuery(CategoryFilters categoryFilters) {
        super(categoryFilters);
        this.categoryFilters = categoryFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
