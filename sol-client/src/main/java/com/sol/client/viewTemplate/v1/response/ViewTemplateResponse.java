package com.sol.client.viewTemplate.v1.response;

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
public class ViewTemplateResponse {
    
    @ApiModelProperty("Идентификатор")
    protected String id;
    @ApiModelProperty("ownerId")
    protected String ownerId;
    @ApiModelProperty("title")
    protected String title;
    @ApiModelProperty("description")
    protected String description;
    @ApiModelProperty("createdFromViewId")
    protected String createdFromViewId;
    @ApiModelProperty("status")
    protected ViewTemplateEntity.Status status;
    @ApiModelProperty("ownerType")
    protected ViewTemplateEntity.OwnerType ownerType;
    @ApiModelProperty("language")
    protected ViewTemplateEntity.Language language;
    @ApiModelProperty("view")
    protected View view;
    @ApiModelProperty("addByDefault")
    protected Boolean addByDefault;
    @ApiModelProperty("canEdit")
    protected Boolean canEdit;


    
    
}
