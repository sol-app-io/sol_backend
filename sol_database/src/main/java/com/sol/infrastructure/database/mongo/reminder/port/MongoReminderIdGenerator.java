package com.sol.infrastructure.database.mongo.reminder.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.reminder.port.ReminderIdGenerator;

@Component
public class MongoReminderIdGenerator extends ObjectIdGenerator implements ReminderIdGenerator<ObjectId> {
}
