package com.sol.client.task.v1.mappers;

import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.task.entity.TaskEntity;

import java.time.ZoneOffset;
import java.util.ArrayList;
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
                .deadline(entity.getDeadlineMill())
                .deadlineType(entity.getDeadlineType())
                .timezone(entity.getTimezone())
                .repeatTaskConfId(entity.getRepeatTaskConfId())
                .createdFromRepeatTaskId(entity.getCreatedFromRepeatTaskId())
                .pics(entity.getPics())
                .files(entity.getFiles())
                .description(entity.getDescription())
                .externalIds(entity.getExternalIds())
                .slotsMilliseconds(entity.getSlotsMilliseconds())
                .status(entity.getStatus())
                .child(new ArrayList<>())
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
                .deadline(entity.getDeadlineMill())
                .deadlineType(entity.getDeadlineType())
                .timezone(entity.getTimezone())
                .repeatTaskConfId(entity.getRepeatTaskConfId())
                .createdFromRepeatTaskId(entity.getCreatedFromRepeatTaskId())
                .pics(entity.getPics())
                .files(entity.getFiles())
                .description(entity.getDescription())
                .externalIds(entity.getExternalIds())
                .slotsMilliseconds(entity.getSlotsMilliseconds())
                .status(entity.getStatus())
                .hasChild(entity.getChild().size() > 0)
                .child(entity.getChild() != null ?
                        entity.getChild().stream().map(TaskResponseMapper::map).collect(Collectors.toList()) :
                        new ArrayList<>())
                .build();
    }

}
