package com.sol.client.viewUser.v1.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskInViewRequest {
    private String taskId;
    private String viewId;
}
