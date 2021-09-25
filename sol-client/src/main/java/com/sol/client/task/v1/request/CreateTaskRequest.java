package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.sol.domain.task.usecases.CreateTaskUseCase;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@ApiModel("Task: для запроса на создание")
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    @ApiModelProperty("parentTaskId")
    protected String parentTaskId;
    @ApiModelProperty("spaceId")
    protected String spaceId;
    @ApiModelProperty("Заголовок")
    protected String title;
    @ApiModelProperty("icon")
    protected Icon icon;
//    @ApiModelProperty("viewIds")
//    protected List<String> viewIds;
//    @ApiModelProperty("planningPoints")
//    protected List<String> planningPoints;
    @ApiModelProperty("deadline")
    protected Long deadline;
    @ApiModelProperty("deadlineType")
    protected DeadlineType deadlineType;
    @ApiModelProperty("timezone")
    protected Integer timezone;
//    @ApiModelProperty("repeatTaskConfId")
//    protected String repeatTaskConfId;

    public CreateTaskUseCase.InputValues toInputValues(String credentialId) {
        return CreateTaskUseCase.InputValues
                .builder()
                .parentTaskId(parentTaskId)
                .credentialId(credentialId)
                .spaceId(spaceId)
                .title(title)
                .icon(icon)
                //.viewIds(viewIds)
                //.planningPoints(planningPoints)
                .deadline(deadline)
                .deadlineType(deadlineType)
                .timezone(timezone)
                //.repeatTaskConfId(repeatTaskConfId)
                .build();
    }

}
