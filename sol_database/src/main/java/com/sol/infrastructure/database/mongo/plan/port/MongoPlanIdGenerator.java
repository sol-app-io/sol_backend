package com.sol.infrastructure.database.mongo.plan.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.plan.port.PlanIdGenerator;

@Component
public class MongoPlanIdGenerator extends ObjectIdGenerator implements PlanIdGenerator<ObjectId> {
}
