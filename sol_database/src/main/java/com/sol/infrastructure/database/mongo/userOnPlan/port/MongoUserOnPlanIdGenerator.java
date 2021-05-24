package com.sol.infrastructure.database.mongo.userOnPlan.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.userOnPlan.port.UserOnPlanIdGenerator;

@Component
public class MongoUserOnPlanIdGenerator extends ObjectIdGenerator implements UserOnPlanIdGenerator<ObjectId> {
}
