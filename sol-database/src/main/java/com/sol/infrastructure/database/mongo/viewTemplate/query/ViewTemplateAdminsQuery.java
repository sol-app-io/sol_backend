package com.sol.infrastructure.database.mongo.viewTemplate.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateAdminsFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class ViewTemplateAdminsQuery extends AbstractExampleQuery {

    private final ViewTemplateAdminsFilters viewTemplateAdminsFilters;

    public ViewTemplateAdminsQuery(ViewTemplateAdminsFilters viewTemplateAdminsFilters) {
        super(viewTemplateAdminsFilters);
        this.viewTemplateAdminsFilters = viewTemplateAdminsFilters;
    }

    @Override
    public Criteria getCriteria() {
        return Criteria.where("ownerType").is(viewTemplateAdminsFilters.getOwnerType());
    }
}