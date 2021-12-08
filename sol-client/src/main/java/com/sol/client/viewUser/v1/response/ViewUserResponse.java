package com.sol.client.viewUser.v1.response;

import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ApiModel("View Template: общая модель ответа")
public class ViewUserResponse {

    protected String id;
    protected String ownerId;
    protected String createdFromTemplateId;
    protected Boolean hasNewTaskToAdd;
    protected Boolean hasTaskAdded;
    protected Boolean canEdit = true;
    protected View view;

}
