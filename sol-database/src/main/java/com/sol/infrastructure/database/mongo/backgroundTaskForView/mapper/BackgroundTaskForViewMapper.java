package com.sol.infrastructure.database.mongo.backgroundTaskForView.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.infrastructure.database.mongo.backgroundTaskForView.documents.BackgroundTaskForViewDoc;

@Component
@RequiredArgsConstructor
public class BackgroundTaskForViewMapper implements ExampleDataMapper<BackgroundTaskForViewEntity, BackgroundTaskForViewDoc> {
    private final BackgroundTaskForViewIdGenerator<ObjectId> idGenerator;

    @Override
    public BackgroundTaskForViewDoc map(BackgroundTaskForViewEntity entity) {
        return BackgroundTaskForViewDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .taskId(entity.getTaskId())
                .status(entity.getStatus())
                .log(entity.getLog())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public BackgroundTaskForViewEntity inverseMap(BackgroundTaskForViewDoc doc) {
        return BackgroundTaskForViewEntity
                .builder()
                .id(doc.getId().toString())
                .taskId(doc.getTaskId())
                .status(doc.getStatus())
                .log(doc.getLog())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
