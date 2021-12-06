package com.sol.infrastructure.database.mongo.taskInView.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;

@Component
public class MongoTaskInViewIdGenerator extends ObjectIdGenerator implements TaskInViewIdGenerator<ObjectId> {
}
