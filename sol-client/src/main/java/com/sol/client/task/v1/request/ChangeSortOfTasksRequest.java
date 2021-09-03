package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ApiModel("Task: Сортировка")
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSortOfTasksRequest {

    @ApiModelProperty("tasks")
    protected List<String> tasks;

}
