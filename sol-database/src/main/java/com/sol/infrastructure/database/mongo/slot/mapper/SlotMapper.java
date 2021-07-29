package com.sol.infrastructure.database.mongo.slot.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.infrastructure.database.mongo.slot.documents.SlotDoc;

@Component
@RequiredArgsConstructor
public class SlotMapper implements ExampleDataMapper<SlotEntity, SlotDoc> {
    private final SlotIdGenerator<ObjectId> idGenerator;

    @Override
    public SlotDoc map(SlotEntity entity) {
        return (SlotDoc) entity;
    }

    @Override
    public SlotEntity inverseMap(SlotDoc doc) {
        return doc;
    }
}
