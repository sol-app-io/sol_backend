package com.sol.infrastructure.database.mongo.plan.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.plan.entity.PlanEntity;
import com.sol.domain.plan.port.PlanIdGenerator;
import com.sol.infrastructure.database.mongo.plan.documents.PlanDoc;

@Component
@RequiredArgsConstructor
public class PlanMapper implements ExampleDataMapper<PlanEntity, PlanDoc> {
    private final PlanIdGenerator<ObjectId> idGenerator;

    @Override
    public PlanDoc map(PlanEntity entity) {
        return (PlanDoc) entity;
    }

    @Override
    public PlanEntity inverseMap(PlanDoc doc) {
        return doc;
    }
}
