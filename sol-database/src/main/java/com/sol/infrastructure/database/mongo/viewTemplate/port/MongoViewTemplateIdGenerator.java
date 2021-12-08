package com.sol.infrastructure.database.mongo.viewTemplate.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;

@Component
public class MongoViewTemplateIdGenerator extends ObjectIdGenerator implements ViewTemplateIdGenerator<ObjectId> {
}
