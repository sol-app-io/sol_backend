package com.sol.client.category.v1.request;

import lombok.*;
import com.sol.domain.category.usecases.CreateCategoryUseCase;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    protected String title;
    protected String description;
    protected String parentId;



    public CreateCategoryUseCase.InputValues toInputValues() {
        return CreateCategoryUseCase.InputValues
                .builder()
                .title(title)
                .description(description)
                .parentId(parentId)
                .build();
    }

}
