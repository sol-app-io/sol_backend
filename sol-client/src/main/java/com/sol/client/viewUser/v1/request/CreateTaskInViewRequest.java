package com.sol.client.viewUser.v1.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ApiModel("CreateTaskInViewRequest")
public class CreateTaskInViewRequest {
    private String taskId;
    private String viewId;
}
