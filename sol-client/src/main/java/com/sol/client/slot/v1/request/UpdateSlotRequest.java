package com.sol.client.slot.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.slot.usecases.UpdateSlotUseCase;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.UpdateSolUserUseCase;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.usecases.UpdateTaskUseCase;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSlotRequest {

    protected String id;
    protected Long startTime;
    protected Long endTime;
    protected Integer timezone;

    public UpdateSlotUseCase.InputValues toInputValues(SolUserEntity solUserEntity) {
        return UpdateSlotUseCase.InputValues.of(id, solUserEntity.getId(), startTime, endTime, timezone);
    }

}
