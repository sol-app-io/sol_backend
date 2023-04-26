package com.sol.client.category.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
    
    protected String id;
    protected String title;
    protected String description;
    protected String parentId;


    
    
}
