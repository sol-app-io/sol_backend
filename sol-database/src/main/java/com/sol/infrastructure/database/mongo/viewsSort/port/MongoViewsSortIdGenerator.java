package com.sol.infrastructure.database.mongo.viewsSort.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;

@Component
public class MongoViewsSortIdGenerator extends ObjectIdGenerator implements ViewsSortIdGenerator<ObjectId> {
}
