package com.sol.client.category.v1.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.sol.domain.category.usecases.CreateCategoryUseCase;

@Builder
@Getter
@Setter
@ApiModel("Место покупателя: для запроса на создание")
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @ApiModelProperty("Title")
    protected String title;
    @ApiModelProperty("Description")
    protected String description;
    @ApiModelProperty("Parent category")
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
