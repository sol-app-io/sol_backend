package com.sol.infrastructure.database.mongo.viewUser.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class ViewUserByTemplateQuery extends AbstractExampleQuery {

    private final ViewUserByTemplateFilters viewUserByTemplateFilters;

    public ViewUserByTemplateQuery(ViewUserByTemplateFilters viewUserByTemplateFilters) {
        super(viewUserByTemplateFilters);
        this.viewUserByTemplateFilters = viewUserByTemplateFilters;
    }

    @Override
    public Criteria getCriteria() {
        return Criteria.where("createdFromTemplateId").is(viewUserByTemplateFilters.getTemplateId());
    }
}