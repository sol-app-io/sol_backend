package com.sol.infrastructure.database.mongo.viewUser.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.infrastructure.database.mongo.viewUser.documents.ViewUserDoc;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewUserMapper implements ExampleDataMapper<ViewUserEntity, ViewUserDoc> {
    private final ViewUserIdGenerator<ObjectId> idGenerator;

    @Override
    public ViewUserDoc map(ViewUserEntity entity) {
        return ViewUserDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .ownerId(entity.getOwnerId())
                .createdFromTemplateId(entity.getCreatedFromTemplateId())
                .hasNewTaskToAdd(entity.getHasNewTaskToAdd())
                .hasTaskAdded(entity.getHasTaskAdded())
                .canEdit(entity.getCanEdit())
                .view(entity.getView())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public ViewUserEntity inverseMap(ViewUserDoc doc) {
        return ViewUserEntity
                .builder()
                .id(doc.getId().toString())
                .ownerId(doc.getOwnerId())
                .createdFromTemplateId(doc.getCreatedFromTemplateId())
                .hasNewTaskToAdd(doc.getHasNewTaskToAdd())
                .hasTaskAdded(doc.getHasTaskAdded())
                .canEdit(doc.getCanEdit())
                .view(doc.getView())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
