package com.sol.client.slot.v1.mappers;

import com.sol.client.slot.v1.response.SlotResponse;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.infrastructure.database.mongo.base.ObjectIdHelper;
import com.sol.infrastructure.database.mongo.slot.documents.SlotDoc;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SlotResponseMapper {
    public SlotResponse map(SlotEntity entity) {
        return SlotResponse.builder()
                .id((entity.getId()))
                .title(entity.getTitle())
                .ownerId((entity.getOwnerId()))
                .createdFromTaskId((entity.getCreatedFromTaskId()))
                .spaceId((entity.getSpaceId()))
                .viewIds((entity.getViewIds()))
                .startTime(DateUtils.convert(entity.getStartTime(), entity.getTimezone()))
                .endTime(DateUtils.convert(entity.getEndTime(), entity.getTimezone()))
                .slotsMilliseconds(entity.getSlotsMilliseconds())
                .externalIds(entity.getExternalIds())
                .timezone(entity.getTimezone())
                .createdAt(DateUtils.convert(entity.getCreatedAt(), entity.getTimezone()))
                .updatedAt(DateUtils.convert(entity.getUpdatedAt(), entity.getTimezone()))
                .build();
    }


}
