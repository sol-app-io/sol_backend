package com.sol.domain.taskInView.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Task in view
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class TaskInViewEntity extends BaseEntity<String> {


    /**
     * view 
     */
    protected String viewId;
    /**
     * Task 
     */
    protected String taskId;
    /**
     * Sort num 
     */
    protected Integer sortNum;


    public TaskInViewEntity(String id) {
        this.id = id;
    }

}
