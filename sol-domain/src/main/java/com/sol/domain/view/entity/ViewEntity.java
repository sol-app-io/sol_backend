package com.sol.domain.view.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.base.entity.Icon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * View
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ViewEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Space 
     */
    protected String spaceId;
    /**
     * Title 
     */
    protected String title;
    /**
     * Icon 
     */
    protected Icon icon = new Icon();

    /**
     * Type 
     */
    protected ViewType type = ViewType.MANUAL;

    /************************************ Constructors ************************************/

    public ViewEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
