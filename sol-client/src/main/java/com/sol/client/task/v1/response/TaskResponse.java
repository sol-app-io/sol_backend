package com.sol.client.task.v1.response;

import com.sol.domain.base.entity.Icon;
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
public class TaskResponse {

    @Builder
    @Getter
    @Setter
    public static class ChildTask{
        @ApiModelProperty("Идентификатор")
        protected String id;
        @ApiModelProperty("ownerId")
        protected String ownerId;
        @ApiModelProperty("parentTaskId")
        protected String parentTaskId;
        @ApiModelProperty("spaceId")
        protected String spaceId;
        @ApiModelProperty("Заголовок")
        protected String title;
        @ApiModelProperty("icon")
        protected Icon icon;
        @ApiModelProperty("viewIds")
        protected List<String> viewIds;
        @ApiModelProperty("planningPoints")
        protected List<String> planningPoints;
        @ApiModelProperty("deadline")
        protected LocalDateTime deadline;
        @ApiModelProperty("repeatTaskConfId")
        protected String repeatTaskConfId;
        @ApiModelProperty("createdFromRepeatTaskId")
        protected String createdFromRepeatTaskId;
        @ApiModelProperty("pics")
        protected List<String> pics;
        @ApiModelProperty("files")
        protected List<String> files;
        @ApiModelProperty("description")
        protected String description;
        @ApiModelProperty("externalIds")
        protected List<String> externalIds;
        @ApiModelProperty("pointWeight")
        protected Integer pointWeight;
        @ApiModelProperty("status")
        protected TaskStatus status;
        @ApiModelProperty("hasChild")
        protected Boolean hasChild = false;
    }

    @ApiModelProperty("Идентификатор")
    protected String id;
    @ApiModelProperty("ownerId")
    protected String ownerId;
    @ApiModelProperty("parentTaskId")
    protected String parentTaskId;
    @ApiModelProperty("spaceId")
    protected String spaceId;
    @ApiModelProperty("Заголовок")
    protected String title;
    @ApiModelProperty("icon")
    protected Icon icon;
    @ApiModelProperty("viewIds")
    protected List<String> viewIds;
    @ApiModelProperty("planningPoints")
    protected List<String> planningPoints;
    @ApiModelProperty("deadline")
    protected LocalDateTime deadline;
    @ApiModelProperty("repeatTaskConfId")
    protected String repeatTaskConfId;
    @ApiModelProperty("createdFromRepeatTaskId")
    protected String createdFromRepeatTaskId;
    @ApiModelProperty("pics")
    protected List<String> pics;
    @ApiModelProperty("files")
    protected List<String> files;
    @ApiModelProperty("description")
    protected String description;
    @ApiModelProperty("externalIds")
    protected List<String> externalIds;
    @ApiModelProperty("pointWeight")
    protected Integer pointWeight;
    @ApiModelProperty("status")
    protected TaskStatus status;
    @ApiModelProperty("hasChild")
    protected Boolean hasChild = false;
    protected List<ChildTask> child = new ArrayList<>();
}
