package com.sol.infrastructure.database.mongo.solUser.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.solUser.port.SolUserIdGenerator;

@Component
public class MongoSolUserIdGenerator extends ObjectIdGenerator implements SolUserIdGenerator<ObjectId> {
}
