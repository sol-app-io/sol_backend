package com.sol.domain.viewsSort.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание сущности, если требуется
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ViewsSortEntity extends BaseEntity<String> {
    public enum Type{
        ROOT, SPACE, TASK
    }

    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Type 
     */
    protected Type type;
    /**
     * Views 
     */
    protected List<String> viewsId = new ArrayList<>();


    public ViewsSortEntity(String id) {
        this.id = id;
    }

}
