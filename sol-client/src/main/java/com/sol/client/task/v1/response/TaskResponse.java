package com.sol.client.task.v1.response;

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
public class TaskResponse {

    protected String id;
    protected String ownerId;
    protected String parentTaskId;
    protected String spaceId;
    protected String title;
    protected Icon icon;
    protected Set<String> viewIds;
    protected Set<String> suggestForViewIds;
    protected List<String> planningPoints;
    protected Long deadline;
    protected DeadlineType deadlineType;
    protected Boolean deadlineChangeFewTimes ;
    protected Integer timezone;
    protected String repeatTaskConfId;
    protected String createdFromRepeatTaskId;
    protected List<String> pics;
    protected List<String> files;
    protected String description;
    protected List<String> externalIds;
    protected Long slotsMilliseconds;
    protected TaskStatus status;
    protected Boolean hasChild = false;
    protected List<TaskResponse> child = new ArrayList<>();
}
