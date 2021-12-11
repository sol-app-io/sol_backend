package com.sol.client.viewUser.v1.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Builder
@Getter
@Setter
@ApiModel("CreateTaskInViewRequest")
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskInViewRequest {
    private String taskId;
    private String viewId;
}
