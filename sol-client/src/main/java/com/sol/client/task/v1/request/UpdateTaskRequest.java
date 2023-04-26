package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.TaskStatus;
import lombok.*;
import com.sol.domain.task.usecases.UpdateTaskUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {

    protected String parentTaskId;
    protected String spaceId;
    protected String title;
    protected Icon icon;
    protected List<String> planningPoints;
    protected LocalDateTime deadline;
    protected String repeatTaskConfId;
    protected String createdFromRepeatTaskId;
    protected List<String> pics;
    protected List<String> files;
    protected String description;
    protected List<String> externalIds;
    protected Long slotsMilliseconds;
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
