package com.sol.client.viewUser.v1.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ApiModel("View")
public class TaskInViewResponse {
    private String viewId;
    private String taskId;
    private Integer sortNum;
}
