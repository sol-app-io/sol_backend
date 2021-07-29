package com.sol.infrastructure.database.mongo.reminder.query;

import com.rcore.database.mongo.commons.query.AbstractExampleQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import com.sol.domain.reminder.port.filters.ReminderFilters;

public class FindWithFiltersQuery extends AbstractExampleQuery  {

    private final ReminderFilters reminderFilters;

    public FindWithFiltersQuery(ReminderFilters reminderFilters) {
        super(reminderFilters);
        this.reminderFilters = reminderFilters;
    }

    @Override
    public Criteria getCriteria() {
        return new Criteria();
    }
}
