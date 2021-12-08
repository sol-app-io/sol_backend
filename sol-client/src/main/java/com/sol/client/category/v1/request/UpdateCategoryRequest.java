package com.sol.client.category.v1.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.sol.domain.category.usecases.UpdateCategoryUseCase;

@Builder
@Getter
@Setter
@ApiModel("Место покупателя: для запроса на обновление")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {

    @ApiModelProperty("Title")
    protected String title;
    @ApiModelProperty("Description")
    protected String description;
    @ApiModelProperty("Parent category")
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
