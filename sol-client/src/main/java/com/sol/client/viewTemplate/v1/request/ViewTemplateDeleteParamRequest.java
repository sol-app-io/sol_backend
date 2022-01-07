package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.view.entity.View;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewTemplateDeleteParamRequest {
    private String idTemplate;
    protected String id;
}
