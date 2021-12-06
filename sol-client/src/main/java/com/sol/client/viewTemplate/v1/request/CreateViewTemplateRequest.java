package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.viewTemplate.usecases.CreateViewTemplateByAdminUseCase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@ApiModel("View Template: для запроса на создание")
@NoArgsConstructor
@AllArgsConstructor
public class CreateViewTemplateRequest {

    @ApiModelProperty("title")
    protected String title;
    @ApiModelProperty("description")
    protected String description;

    public CreateViewTemplateByAdminUseCase.InputValues toInputValues() {
        return CreateViewTemplateByAdminUseCase.InputValues
                .builder()
                .title(title)
                .description(description)
                .build();
    }

}
