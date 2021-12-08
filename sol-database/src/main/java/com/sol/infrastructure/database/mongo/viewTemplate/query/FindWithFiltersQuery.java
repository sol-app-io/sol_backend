package com.sol.infrastructure.database.mongo.viewTemplate.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final ViewTemplateFilters viewTemplateFilters;

    public FindWithFiltersQuery(ViewTemplateFilters viewTemplateFilters) {
        super(viewTemplateFilters);
        this.viewTemplateFilters = viewTemplateFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
