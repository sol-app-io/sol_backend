package com.sol.infrastructure.database.mongo.repeatTaskConf.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfIdGenerator;
import com.sol.infrastructure.database.mongo.repeatTaskConf.documents.RepeatTaskConfDoc;

@Component
@RequiredArgsConstructor
public class RepeatTaskConfMapper implements ExampleDataMapper<RepeatTaskConfEntity, RepeatTaskConfDoc> {
    private final RepeatTaskConfIdGenerator<ObjectId> idGenerator;

    @Override
    public RepeatTaskConfDoc map(RepeatTaskConfEntity entity) {
        return (RepeatTaskConfDoc) entity;
    }

    @Override
    public RepeatTaskConfEntity inverseMap(RepeatTaskConfDoc doc) {
        return doc;
    }
}
