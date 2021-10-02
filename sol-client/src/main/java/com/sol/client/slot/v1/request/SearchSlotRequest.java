package com.sol.client.slot.v1.request;

import com.rcore.rest.api.commons.request.SearchApiRequest;
import com.sol.domain.slot.port.filters.SlotFilters;
import com.sol.domain.slot.usecases.FindByDateSlotUseCase;
import com.sol.domain.slot.usecases.FindByTaskSlotUseCase;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.task.port.filters.TaskFilters;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@ApiModel("Slot: для запроса на поиск")
@Getter
@Setter
public class SearchSlotRequest  {
    private String taskId;
    private Long date;
    private Integer timezone;

    public FindByTaskSlotUseCase.InputValues toTaskInputValues(SolUserEntity solUserEntity) {
        return FindByTaskSlotUseCase.InputValues.of(solUserEntity.getId(), taskId);
    }

    public FindByDateSlotUseCase.InputValues toDateInputValues(SolUserEntity solUserEntity) {
        return FindByDateSlotUseCase.InputValues.of(solUserEntity.getId(), date, timezone);
    }
}
