package com.sol.infrastructure.database.mongo.reminder.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.reminder.entity.ReminderEntity;
import com.sol.domain.reminder.port.ReminderIdGenerator;
import com.sol.infrastructure.database.mongo.reminder.documents.ReminderDoc;

@Component
@RequiredArgsConstructor
public class ReminderMapper implements ExampleDataMapper<ReminderEntity, ReminderDoc> {
    private final ReminderIdGenerator<ObjectId> idGenerator;

    @Override
    public ReminderDoc map(ReminderEntity entity) {
        return (ReminderDoc)entity;
    }

    @Override
    public ReminderEntity inverseMap(ReminderDoc doc) {
        return doc;
    }
}
