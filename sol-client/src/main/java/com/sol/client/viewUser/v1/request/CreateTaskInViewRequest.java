package com.sol.client.viewUser.v1.request;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ApiModel("CreateTaskInViewRequest")
public class CreateTaskInViewRequest {
    private String taskId;
    private String viewId;
    private Integer sortNum;
}
