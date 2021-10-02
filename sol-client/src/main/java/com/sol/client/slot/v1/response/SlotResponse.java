package com.sol.client.slot.v1.response;

import com.sol.domain.base.entity.ExternalId;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@ApiModel("Task: общая модель ответа")
public class SlotResponse {

    @ApiModelProperty("Id")
    protected String id;
    @ApiModelProperty("title")
    protected String title;
    @ApiModelProperty("ownerId")
    protected String ownerId;
    @ApiModelProperty("createdFromTaskId")
    protected String createdFromTaskId;
    @ApiModelProperty("spaceId")
    protected String spaceId;
    @ApiModelProperty("viewIds")
    protected List<String> viewIds;
    @ApiModelProperty("startTime")
    protected Long startTime;
    @ApiModelProperty("endTime")
    protected Long endTime;
    @ApiModelProperty("slotsMilliseconds")
    protected Long slotsMilliseconds = 0l;
    @ApiModelProperty("externalIds")
    protected List<ExternalId> externalIds = new ArrayList<>();
    @ApiModelProperty("timezone")
    protected Integer timezone = 0;
    @ApiModelProperty("createdAt")
    protected Long createdAt;
    @ApiModelProperty("updatedAt")
    protected Long updatedAt;


}
