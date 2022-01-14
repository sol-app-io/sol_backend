package com.sol.domain.viewUser.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.view.entity.View;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * user&#39;s view
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ViewUserEntity extends BaseEntity<String> {


    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Created from template 
     */
    protected String createdFromTemplateId;
    /**
     * Has new task 
     */
    protected Boolean hasNewTaskToAdd;
    /**
     * Has new task 
     */
    protected Boolean hasTaskAdded;
    protected Boolean canEdit = true;
    /**
     * view 
     */
    protected View view = new View();



    public ViewUserEntity(String id) {
        this.id = id;
    }

}
