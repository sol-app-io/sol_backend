package com.sol.client.slot.v1.response;

import com.sol.domain.base.entity.ExternalId;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class SlotResponse {

    protected String id;
    protected String title;
    protected String ownerId;
    protected String createdFromTaskId;
    protected String spaceId;
    protected Set<String> viewIds;
    protected Long startTime;
    protected Long endTime;
    protected Long slotsMilliseconds = 0l;
    protected List<ExternalId> externalIds = new ArrayList<>();
    protected Integer timezone = 0;
    protected Long createdAt;
    protected Long updatedAt;


}
