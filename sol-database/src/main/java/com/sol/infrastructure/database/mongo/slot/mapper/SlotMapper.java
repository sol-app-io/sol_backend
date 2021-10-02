package com.sol.infrastructure.database.mongo.slot.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.sol.domain.base.entity.ExternalId;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.infrastructure.database.mongo.slot.documents.SlotDoc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SlotMapper implements ExampleDataMapper<SlotEntity, SlotDoc> {
    private final SlotIdGenerator<ObjectId> idGenerator;

    @Override
    public SlotDoc map(SlotEntity entity) {
        return SlotDoc.builder()
                .id(ObjectIdHelper.mapOrDie(entity.getId()))
                .title(entity.getTitle())
                .ownerId(ObjectIdHelper.mapOrDie(entity.getOwnerId()))
                .createdFromTaskId(ObjectIdHelper.mapOrDie(entity.getCreatedFromTaskId()))
                .spaceId(ObjectIdHelper.mapOrDie(entity.getSpaceId()))
                .viewIds(ObjectIdHelper.mapOrDie(entity.getViewIds()))
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .slotsMilliseconds(entity.getSlotsMilliseconds())
                .externalIds(entity.getExternalIds())
                .timezone(entity.getTimezone())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public SlotEntity inverseMap(SlotDoc doc) {
        return SlotEntity.builder()
                .id(ObjectIdHelper.toString(doc.getId()))
                .title(doc.getTitle())
                .ownerId(ObjectIdHelper.toString(doc.getOwnerId()))
                .createdFromTaskId(ObjectIdHelper.toString(doc.getCreatedFromTaskId()))
                .spaceId(ObjectIdHelper.toString(doc.getSpaceId()))
                .viewIds(ObjectIdHelper.toString(doc.getViewIds()))
                .startTime(doc.getStartTime())
                .endTime(doc.getEndTime())
                .slotsMilliseconds(doc.getSlotsMilliseconds())
                .externalIds(doc.getExternalIds())
                .timezone(doc.getTimezone())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
