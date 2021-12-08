package com.sol.infrastructure.database.mongo.viewTemplate.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.infrastructure.database.mongo.viewTemplate.documents.ViewTemplateDoc;

@Component
@RequiredArgsConstructor
public class ViewTemplateMapper implements ExampleDataMapper<ViewTemplateEntity, ViewTemplateDoc> {
    private final ViewTemplateIdGenerator<ObjectId> idGenerator;

    @Override
    public ViewTemplateDoc map(ViewTemplateEntity entity) {
        return ViewTemplateDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .ownerId(entity.getOwnerId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createdFromViewId(entity.getCreatedFromViewId())
                .status(entity.getStatus())
                .ownerType(entity.getOwnerType())
                .language(entity.getLanguage())
                .view(entity.getView())
                .addByDefault(entity.getAddByDefault())
                .canEdit(entity.getCanEdit())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public ViewTemplateEntity inverseMap(ViewTemplateDoc doc) {
        return ViewTemplateEntity
                .builder()
                .id(doc.getId().toString())
                .ownerId(doc.getOwnerId())
                .title(doc.getTitle())
                .description(doc.getDescription())
                .createdFromViewId(doc.getCreatedFromViewId())
                .status(doc.getStatus())
                .ownerType(doc.getOwnerType())
                .language(doc.getLanguage())
                .view(doc.getView())
                .addByDefault(doc.getAddByDefault())
                .canEdit(doc.getCanEdit())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
