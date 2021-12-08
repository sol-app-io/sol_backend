package com.sol.infrastructure.database.mongo.taskInView.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.taskInView.entity.TaskInViewEntity;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.infrastructure.database.mongo.taskInView.documents.TaskInViewDoc;

@Component
@RequiredArgsConstructor
public class TaskInViewMapper implements ExampleDataMapper<TaskInViewEntity, TaskInViewDoc> {
    private final TaskInViewIdGenerator<ObjectId> idGenerator;

    @Override
    public TaskInViewDoc map(TaskInViewEntity entity) {
        return TaskInViewDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .viewId(entity.getViewId())
                .taskId(entity.getTaskId())
                .sortNum(entity.getSortNum())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public TaskInViewEntity inverseMap(TaskInViewDoc doc) {
        return TaskInViewEntity
                .builder()
                .id(doc.getId().toString())
                .viewId(doc.getViewId())
                .taskId(doc.getTaskId())
                .sortNum(doc.getSortNum())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
