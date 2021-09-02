package com.sol.domain.space.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.base.entity.Icon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Space
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SpaceEntity extends BaseEntity<String> {

    public enum Type {
        DEFAULT, INBOX;
    }

    /************************************ Fields ************************************/

    /**
     * Title
     */
    protected String title;
    /**
     * Icon
     */
    protected Icon icon = new Icon();
    /**
     * Owner
     */
    protected String ownerId;

    protected Integer sortNum;

    protected Type type = Type.DEFAULT;

    /************************************ Constructors ************************************/

    public SpaceEntity(String id) {
        this.id = id;
    }

    /************************************ Methods ************************************/

    public Boolean checkAccess(String solUserId) {
        if(this.ownerId.equals(solUserId)) return true;
        return false;
    }
}
