package com.sol.domain.plan.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Описание сущности, если требуется
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class PlanEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * title 
     */
    protected String title;
    /**
     * description 
     */
    protected String description;
    /**
     * currency 
     */
    protected String currency = "RUB";
    /**
     * price 
     */
    protected Double price;
    /**
     * status 
     */
    protected PlanStatus status = PlanStatus.DRAFT;
    /**
     * period 
     */
    protected Integer period;

    /************************************ Constructors ************************************/

    public PlanEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
