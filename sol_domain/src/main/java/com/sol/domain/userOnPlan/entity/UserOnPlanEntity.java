package com.sol.domain.userOnPlan.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Описание сущности, если требуется
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserOnPlanEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * userId 
     */
    protected String userId;
    /**
     * planId 
     */
    protected String planId;
    /**
     * startDate 
     */
    protected LocalDate startDate;
    /**
     * endDate 
     */
    protected LocalDate endDate;

    /************************************ Constructors ************************************/

    public UserOnPlanEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
