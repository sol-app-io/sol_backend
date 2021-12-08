package com.sol.infrastructure.database.mongo.category.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.port.CategoryIdGenerator;
import com.sol.infrastructure.database.mongo.category.documents.CategoryDoc;

@Component
@RequiredArgsConstructor
public class CategoryMapper implements ExampleDataMapper<CategoryEntity, CategoryDoc> {
    private final CategoryIdGenerator<ObjectId> idGenerator;

    @Override
    public CategoryDoc map(CategoryEntity entity) {
        return CategoryDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .parentId(ObjectIdHelper.mapOrDie(entity.getParentId()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public CategoryEntity inverseMap(CategoryDoc doc) {
        return CategoryEntity
                .builder()
                .id(doc.getId().toString())
                .title(doc.getTitle())
                .description(doc.getDescription())
                .parentId(doc.getParentId() != null ? doc.getParentId().toString() : null)
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
