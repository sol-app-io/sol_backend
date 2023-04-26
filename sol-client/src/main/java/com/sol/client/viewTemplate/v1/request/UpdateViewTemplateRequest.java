package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.usecases.UpdateViewInTemplateByAdminUseCase;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateViewTemplateRequest {

    protected String title;
    protected String description;

    protected String viewTitle;
    protected String viewDescription;

    protected String iconEmoji;

    protected View.ViewType viewType;
    protected View.AddedType addedType;
    protected View.DisplayMode displayMode;
    protected Boolean canEdit;
    protected Boolean addByDefault;
    protected ViewTemplateEntity.Status status;

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
                .viewType(viewType)
                .displayMode(displayMode)
                .canEdit(canEdit)
                .addByDefault(addByDefault)
                .status(status)
                .build();
    }

}
