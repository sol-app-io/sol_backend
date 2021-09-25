package com.sol.client.task.v1.mappers;

import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.task.entity.TaskEntity;

import java.time.ZoneOffset;
import java.util.stream.Collectors;

public class TaskResponseMapper {
    public static TaskResponse map(TaskEntity entity) {
        return TaskResponse.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .parentTaskId(entity.getParentTaskId())
                .spaceId(entity.getSpaceId())
                .title(entity.getTitle())
                .icon(entity.getIcon())
                .viewIds(entity.getViewIds())
                .planningPoints(entity.getPlanningPoints())
                .deadline(entity.getDeadline().toInstant(ZoneOffset.UTC).toEpochMilli())
                .deadlineType(entity.getDeadlineType())
                .timezone(entity.getTimezone())
                .repeatTaskConfId(entity.getRepeatTaskConfId())
                .createdFromRepeatTaskId(entity.getCreatedFromRepeatTaskId())
                .pics(entity.getPics())
                .files(entity.getFiles())
                .description(entity.getDescription())
                .externalIds(entity.getExternalIds())
                .pointWeight(entity.getPointWeight())
                .status(entity.getStatus())
                .build();
    }

    public static TaskResponse map(TaskEntity.TaskWithChildEntity entity) {
        return TaskResponse.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .parentTaskId(entity.getParentTaskId())
                .spaceId(entity.getSpaceId())
                .title(entity.getTitle())
                .icon(entity.getIcon())
                .viewIds(entity.getViewIds())
                .planningPoints(entity.getPlanningPoints())
                .deadline(entity.getDeadline().toInstant(ZoneOffset.UTC).toEpochMilli())
                .deadlineType(entity.getDeadlineType())
                .timezone(entity.getTimezone())
                .repeatTaskConfId(entity.getRepeatTaskConfId())
                .createdFromRepeatTaskId(entity.getCreatedFromRepeatTaskId())
                .pics(entity.getPics())
                .files(entity.getFiles())
                .description(entity.getDescription())
                .externalIds(entity.getExternalIds())
                .pointWeight(entity.getPointWeight())
                .status(entity.getStatus())
                .hasChild(entity.getChild().size() > 0)
                .child(entity.getChild().stream().map(TaskResponseMapper::map).collect(Collectors.toList()))
                .build();
    }

}
