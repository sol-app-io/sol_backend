package com.sol.infrastructure.database.mongo.viewsSort.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.infrastructure.database.mongo.viewsSort.documents.ViewsSortDoc;

@Component
@RequiredArgsConstructor
public class ViewsSortMapper implements ExampleDataMapper<ViewsSortEntity, ViewsSortDoc> {
    private final ViewsSortIdGenerator<ObjectId> idGenerator;

    @Override
    public ViewsSortDoc map(ViewsSortEntity entity) {
        return ViewsSortDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .ownerId(entity.getOwnerId())
                .type(entity.getType())
                .viewsId(entity.getViewsId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public ViewsSortEntity inverseMap(ViewsSortDoc doc) {
        return ViewsSortEntity
                .builder()
                .id(doc.getId().toString())
                .ownerId(doc.getOwnerId())
                .type(doc.getType())
                .viewsId(doc.getViewsId())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
