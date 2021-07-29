package com.sol.infrastructure.database.mongo.slot.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.slot.port.SlotIdGenerator;

@Component
public class MongoSlotIdGenerator extends ObjectIdGenerator implements SlotIdGenerator<ObjectId> {
}
