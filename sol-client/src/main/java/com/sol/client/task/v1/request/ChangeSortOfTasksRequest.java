package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSortOfTasksRequest {

    protected List<String> tasks;

}
