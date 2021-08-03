package com.sol.infrastructure.database.mongo.task.mapper;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.infrastructure.database.mongo.task.documents.TaskDoc;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskMapper implements ExampleDataMapper<TaskEntity, TaskDoc> {
    private final TaskIdGenerator<ObjectId> idGenerator;



    @Override
    public TaskDoc map(TaskEntity entity) {
        return TaskDoc
                .builder()
                .id(idGenerator.parse(entity.getId()))
                .ownerId(ObjectIdHelper.mapOrDie(entity.getOwnerId()))
                .parentTaskId(ObjectIdHelper.mapOrDie(entity.getParentTaskId()))
                .spaceId(ObjectIdHelper.mapOrDie(entity.getSpaceId()))
                .title(entity.getTitle())
                .icon(entity.getIcon())
                .viewIds(ObjectIdHelper.mapOrDie(entity.getViewIds()))
                .planningPoints(ObjectIdHelper.mapOrDie(entity.getPlanningPoints()))
                .deadline(entity.getDeadline())
                .repeatTaskConfId(ObjectIdHelper.mapOrDie(entity.getRepeatTaskConfId()))
                .createdFromRepeatTaskId(ObjectIdHelper.mapOrDie(entity.getCreatedFromRepeatTaskId()))
                .pics(ObjectIdHelper.mapOrDie(entity.getPics()))
                .files(ObjectIdHelper.mapOrDie(entity.getFiles()))
                .description(entity.getDescription())
                .externalIds(entity.getExternalIds())
                .pointWeight(entity.getPointWeight())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public TaskEntity inverseMap(TaskDoc doc) {
        return TaskEntity
                .builder()
                .id(doc.getId().toString())
                .ownerId(doc.getOwnerId().toString())
                .parentTaskId(doc.getParentTaskId() != null ? doc.getParentTaskId().toString(): null)
                .spaceId(doc.getSpaceId().toString())
                .title(doc.getTitle())
                .icon(doc.getIcon())
                .viewIds(doc.getViewIds().stream().map((id)-> id.toString()).collect(Collectors.toList()))
                .planningPoints(doc.getPlanningPoints().stream().map((id)-> id.toString()).collect(Collectors.toList()))
                .deadline(doc.getDeadline())
                .repeatTaskConfId(doc.getRepeatTaskConfId() != null ? doc.getRepeatTaskConfId().toString(): null)
                .createdFromRepeatTaskId(doc.getCreatedFromRepeatTaskId() != null ? doc.getCreatedFromRepeatTaskId().toString() : null)
                .pics(doc.getPics().stream().map((id)-> id.toString()).collect(Collectors.toList()))
                .files(doc.getFiles().stream().map((id)-> id.toString()).collect(Collectors.toList()))
                .description(doc.getDescription())
                .externalIds(doc.getExternalIds())
                .pointWeight(doc.getPointWeight())
                .status(doc.getStatus())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}
