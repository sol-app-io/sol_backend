package com.sol.client.viewTemplate.v1.request;

import com.sol.domain.viewTemplate.usecases.CreateViewTemplateByAdminUseCase;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateViewTemplateRequest {

    protected String title;
    protected String description;

    public CreateViewTemplateByAdminUseCase.InputValues toInputValues() {
        return CreateViewTemplateByAdminUseCase.InputValues
                .builder()
                .title(title)
                .description(description)
                .build();
    }

}
