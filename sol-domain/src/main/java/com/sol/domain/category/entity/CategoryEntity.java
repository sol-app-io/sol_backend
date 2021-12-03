package com.sol.domain.category.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Категории для теста
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class CategoryEntity extends BaseEntity<String> {


    /**
     * Title 
     */
    protected String title;
    /**
     * description 
     */
    protected String description;
    /**
     * Parent Category 
     */
    protected String parentId;


    public CategoryEntity(String id) {
        this.id = id;
    }

}
