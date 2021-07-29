package com.sol.domain.slot.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.base.entity.ExternalId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Time slot
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SlotEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * owner 
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
    protected String viewIds;
    /**
     * day 
     */
    protected LocalDate day;
    /**
     * start Time 
     */
    protected LocalDateTime startTime;
    /**
     * End Time 
     */
    protected LocalDateTime endTime;
    /**
     * Points 
     */
    protected Long points;
    /**
     * External IDs 
     */
    protected List<ExternalId> externalIds = new ArrayList<>();

    /************************************ Constructors ************************************/

    public SlotEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
