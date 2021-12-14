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
import java.util.Set;

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
     * title
     */
    protected String title;
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
    protected Set<String> viewIds;
    /**
     * start Time 
     */
    protected LocalDateTime startTime;
    /**
     * End Time 
     */
    protected LocalDateTime endTime;
    /**
     * slotsMilliseconds;
     */
    protected Long slotsMilliseconds = 0l;
    /**
     * External IDs 
     */
    protected List<ExternalId> externalIds = new ArrayList<>();
    protected Integer timezone = 0;

    /************************************ Constructors ************************************/

    public SlotEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
