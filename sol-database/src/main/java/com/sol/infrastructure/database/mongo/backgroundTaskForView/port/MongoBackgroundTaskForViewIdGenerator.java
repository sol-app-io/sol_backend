package com.sol.infrastructure.database.mongo.backgroundTaskForView.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;

@Component
public class MongoBackgroundTaskForViewIdGenerator extends ObjectIdGenerator implements BackgroundTaskForViewIdGenerator<ObjectId> {
}
