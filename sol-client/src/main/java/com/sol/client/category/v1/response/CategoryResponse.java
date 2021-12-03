package com.sol.client.category.v1.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@ApiModel("Место покупателя: общая модель ответа")
public class CategoryResponse {
    
    @ApiModelProperty("Идентификатор")
    protected String id;
    @ApiModelProperty("Title")
    protected String title;
    @ApiModelProperty("Description")
    protected String description;
    @ApiModelProperty("Parent category")
    protected String parentId;


    
    
}
