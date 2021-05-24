package com.sol.infrastructure.database.mongo.space.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.space.port.SpaceIdGenerator;

@Component
public class MongoSpaceIdGenerator extends ObjectIdGenerator implements SpaceIdGenerator<ObjectId> {
}
