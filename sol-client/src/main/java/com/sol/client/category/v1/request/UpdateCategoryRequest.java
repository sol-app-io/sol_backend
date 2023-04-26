package com.sol.client.category.v1.request;

import lombok.*;
import com.sol.domain.category.usecases.UpdateCategoryUseCase;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {

    protected String title;
    protected String description;
    protected String parentId;



    public UpdateCategoryUseCase.InputValues toInputValues(String id) {
        return UpdateCategoryUseCase.InputValues
                .builder()
                .id(id)
                .title(title)
                .description(description)
                .parentId(parentId)
                .build();
    }

}
