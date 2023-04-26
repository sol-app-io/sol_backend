package com.sol.client.viewUser.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaskInViewResponse {
    private String viewId;
    private String taskId;
    private Integer sortNum;
}
