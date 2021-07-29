package com.sol.domain.repeatTaskConf.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * RepeatTaskConf
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RepeatTaskConfEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * ownerId 
     */
    protected String ownerId;
    /**
     * createdFromTaskId 
     */
    protected String createdFromTaskId;
    /**
     * spaceId 
     */
    protected String spaceId;
    /**
     * viewIds 
     */
    protected List<String> viewIds = new ArrayList<>();
    /**
     * lastTaskCreatedFromConfId 
     */
    protected String lastTaskCreatedFromConfId;
    /**
     * RepeatType 
     */
    protected RepeatType repeatType;
    /**
     * repeatValue 
     */
    protected Long repeatValue;
    /**
     * tasksCreatedFromConf 
     */
    protected List<String> tasksCreatedFromConf = new ArrayList<>();

    /************************************ Constructors ************************************/

    public RepeatTaskConfEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
