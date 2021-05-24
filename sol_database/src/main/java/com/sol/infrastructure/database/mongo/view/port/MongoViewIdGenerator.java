package com.sol.infrastructure.database.mongo.view.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.view.port.ViewIdGenerator;

@Component
public class MongoViewIdGenerator extends ObjectIdGenerator implements ViewIdGenerator<ObjectId> {
}
