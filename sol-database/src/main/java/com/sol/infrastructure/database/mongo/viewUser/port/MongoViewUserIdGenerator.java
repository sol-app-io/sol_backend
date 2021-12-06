package com.sol.infrastructure.database.mongo.viewUser.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoViewUserIdGenerator extends ObjectIdGenerator implements ViewUserIdGenerator<ObjectId> {
}
