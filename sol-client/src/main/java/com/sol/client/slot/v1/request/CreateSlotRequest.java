package com.sol.client.slot.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.slot.usecases.CreateSlotUseCase;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.usecases.CreateTaskUseCase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@ApiModel("Slot: для запроса на создание")
@NoArgsConstructor
@AllArgsConstructor
public class CreateSlotRequest {
    protected String taskId;
    protected Long startTime;
    protected Long endTime;
    protected Integer timezone;

    public CreateSlotUseCase.InputValues toInputValues(
            String ownerId,
            TaskEntity taskEntity) {
        return CreateSlotUseCase.InputValues
                .builder()
                .ownerId(ownerId)
                .createdFromTask(taskEntity)
                .startTime(startTime)
                .endTime(endTime)
                .timezone(timezone)
                .build();
    }

}
