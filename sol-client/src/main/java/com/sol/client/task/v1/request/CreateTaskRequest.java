package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskStatus;
import lombok.*;
import com.sol.domain.task.usecases.CreateTaskUseCase;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {

    protected String parentTaskId;
    protected String spaceId;
    protected String title;
    protected Icon icon;
//    @ApiModelProperty("viewIds")
//    protected List<String> viewIds;
//    @ApiModelProperty("planningPoints")
//    protected List<String> planningPoints;
    protected Long deadline;
    protected DeadlineType deadlineType;
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
