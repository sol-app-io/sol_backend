package com.sol.infrastructure.database.mongo.slot.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.slot.port.filters.SlotFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final SlotFilters slotFilters;

    public FindWithFiltersQuery(SlotFilters slotFilters) {
        super(slotFilters);
        this.slotFilters = slotFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
