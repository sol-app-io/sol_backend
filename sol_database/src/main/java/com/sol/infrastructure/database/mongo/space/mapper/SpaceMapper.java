package com.sol.infrastructure.database.mongo.space.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.infrastructure.database.mongo.space.documents.SpaceDoc;

@Component
@RequiredArgsConstructor
public class SpaceMapper implements ExampleDataMapper<SpaceEntity, SpaceDoc> {
    private final SpaceIdGenerator<ObjectId> idGenerator;

    @Override
    public SpaceDoc map(SpaceEntity entity) {
        return (SpaceDoc) entity;
    }

    @Override
    public SpaceEntity inverseMap(SpaceDoc doc) {
        return doc;
    }
}
