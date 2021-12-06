package com.sol.infrastructure.database.mongo.viewsSort.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import com.sol.domain.viewsSort.port.filters.ViewsSortBySolTypeFilters;
import org.springframework.data.mongodb.core.query.Criteria;

public class ViewsSortBySolTypeQuery extends AbstractExampleQuery {

    private final ViewsSortBySolTypeFilters viewsSortBySolTypeFilters;

    public ViewsSortBySolTypeQuery(ViewsSortBySolTypeFilters viewsSortBySolTypeFilters) {
        super(viewsSortBySolTypeFilters);
        this.viewsSortBySolTypeFilters = viewsSortBySolTypeFilters;
    }

    @Override
    public Criteria getCriteria() {
        return Criteria
                .where("ownerId").is(viewsSortBySolTypeFilters.getSolUserId())
                .and("type").is(viewsSortBySolTypeFilters.getType());
    }
}
