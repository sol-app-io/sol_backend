package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.usecases.UpdateViewInTemplateByAdminUseCase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@ApiModel("View Template: для запроса на обновление")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateViewTemplateRequest {

    @ApiModelProperty("title")
    protected String title;
    @ApiModelProperty("description")
    protected String description;

    @ApiModelProperty("title")
    protected String viewTitle;
    @ApiModelProperty("description")
    protected String viewDescription;

    @ApiModelProperty("iconEmoji")
    protected String iconEmoji;


    @ApiModelProperty("addedType")
    protected View.AddedType addedType;
    @ApiModelProperty("displayMode")
    protected View.DisplayMode displayMode;
    @ApiModelProperty("canEdit")
    protected Boolean canEdit;
    @ApiModelProperty("addByDefault")
    protected Boolean addByDefault;
    @ApiModelProperty("status")
    protected ViewTemplateEntity.Status status;

    @ApiModelProperty("idTemplate")
    protected String idTemplate;


    public UpdateViewInTemplateByAdminUseCase.InputValues toInputValues(String id) {
        return UpdateViewInTemplateByAdminUseCase.InputValues
                .builder()
                .idTemplate(id)
                .iconEmoji(iconEmoji)
                .title(title)
                .viewTitle(viewTitle)
                .viewDescription(viewDescription)
                .description(description)
                .addedType(addedType)
                .displayMode(displayMode)
                .canEdit(canEdit)
                .addByDefault(addByDefault)
                .status(status)
                .build();
    }

}
