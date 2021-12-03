package com.sol.infrastructure.database.mongo.category.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.category.port.CategoryIdGenerator;

@Component
public class MongoCategoryIdGenerator extends ObjectIdGenerator implements CategoryIdGenerator<ObjectId> {
}
