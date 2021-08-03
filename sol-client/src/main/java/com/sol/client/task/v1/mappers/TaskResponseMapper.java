package com.sol.client.task.v1.mappers;

import com.sol.client.task.v1.response.TaskResponse;
import com.sol.domain.task.entity.TaskEntity;

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
                .deadline(entity.getDeadline())
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

}
