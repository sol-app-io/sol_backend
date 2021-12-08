package com.sol.infrastructure.database.mongo.viewTemplate.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateDefaultsFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class ViewTemplateDefaultsQuery extends AbstractExampleQuery {

    private final ViewTemplateDefaultsFilters viewTemplateDefaultsFilters;

    public ViewTemplateDefaultsQuery(ViewTemplateDefaultsFilters viewTemplateDefaultsFilters) {
        super(viewTemplateDefaultsFilters);
        this.viewTemplateDefaultsFilters = viewTemplateDefaultsFilters;
    }

    @Override
    public Criteria getCriteria() {
        return Criteria
                .where("addByDefault").is(viewTemplateDefaultsFilters.getAddByDefault())
                .and("ownerType").is(viewTemplateDefaultsFilters.getOwnerType());
    }
}