package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.sol.domain.task.usecases.UpdateTaskUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@ApiModel("Task: для запроса на обновление")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {

    @ApiModelProperty("parentTaskId")
    protected String parentTaskId;
    @ApiModelProperty("spaceId")
    protected String spaceId;
    @ApiModelProperty("Заголовок")
    protected String title;
    @ApiModelProperty("icon")
    protected Icon icon;
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
    @ApiModelProperty("slotsMilliseconds")
    protected Long slotsMilliseconds;
    @ApiModelProperty("status")
    protected TaskStatus status;



    public UpdateTaskUseCase.InputValues toInputValues(String id) {
        return UpdateTaskUseCase.InputValues
                .builder()
                .id(id)
                //.ownerId(ownerId)
                .parentTaskId(parentTaskId)
                .spaceId(spaceId)
                .title(title)
                .icon(icon)
                .planningPoints(planningPoints)
                .deadline(deadline)
                .repeatTaskConfId(repeatTaskConfId)
                .createdFromRepeatTaskId(createdFromRepeatTaskId)
                .pics(pics)
                .files(files)
                .description(description)
                .externalIds(externalIds)
                .status(status)
                .build();
    }

}
