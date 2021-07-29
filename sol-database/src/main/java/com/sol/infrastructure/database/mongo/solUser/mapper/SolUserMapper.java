package com.sol.infrastructure.database.mongo.solUser.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.infrastructure.database.mongo.solUser.documents.SolUserDoc;

@Component
@RequiredArgsConstructor
public class SolUserMapper implements ExampleDataMapper<SolUserEntity, SolUserDoc> {
    private final SolUserIdGenerator<ObjectId> idGenerator;

    @Override
    public SolUserDoc map(SolUserEntity entity) {
        return (SolUserDoc)entity;
    }

    @Override
    public SolUserEntity inverseMap(SolUserDoc doc) {
        return doc;
    }
}
