package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.view.entity.View;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewTemplateEditParamRequest {
    private String idTemplate;
    protected String id;
    protected View.Params.Type type;
    protected String valueString;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime valueDate;
    protected Boolean valueBool;
}
