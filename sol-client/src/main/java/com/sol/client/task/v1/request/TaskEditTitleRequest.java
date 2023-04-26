package com.sol.client.task.v1.request;

import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.usecases.CreateTaskUseCase;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEditTitleRequest {

    protected String title;
    protected Icon icon;

}
