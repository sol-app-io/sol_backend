package com.sol.client.viewUser.v1.request;

import com.sol.domain.view.entity.View;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewParamRequest {
    protected View.Params.Type type;
    protected String valueString;
    protected LocalDateTime valueDate;
    protected Boolean valueBool;
}
