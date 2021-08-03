package com.sol.infrastructure.database.mongo.task.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.task.port.TaskIdGenerator;

@Component
public class MongoTaskIdGenerator extends ObjectIdGenerator implements TaskIdGenerator<ObjectId> {
}
