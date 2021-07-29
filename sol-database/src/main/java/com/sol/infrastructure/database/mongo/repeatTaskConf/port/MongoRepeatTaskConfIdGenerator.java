package com.sol.infrastructure.database.mongo.repeatTaskConf.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfIdGenerator;

@Component
public class MongoRepeatTaskConfIdGenerator extends ObjectIdGenerator implements RepeatTaskConfIdGenerator<ObjectId> {
}
