package com.sol.domain.userOnPlan.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
public class UserOnPlanEntity extends BaseEntity {

    /************************************ Fields ************************************/

    /**
     * Идентификатор сущности
     */
    protected String id;

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
