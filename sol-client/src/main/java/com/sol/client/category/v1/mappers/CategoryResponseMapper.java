package com.sol.client.category.v1.mappers;

import com.sol.client.category.v1.response.CategoryResponse;
import com.sol.domain.category.entity.CategoryEntity;

public class CategoryResponseMapper {
    public static CategoryResponse map(CategoryEntity entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .parentId(entity.getParentId())
                .build();
    }

}
