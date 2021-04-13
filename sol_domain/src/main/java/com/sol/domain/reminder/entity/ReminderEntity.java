package com.sol.domain.reminder.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Описание сущности, если требуется
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ReminderEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * Owner 
     */
    protected String ownerId;
    /**
     * Task 
     */
    protected String taskId;
    /**
     * Space 
     */
    protected String spaceId;
    /**
     * View 
     */
    protected List<String> viewIds = new ArrayList<>();
    /**
     * Reminder Time 
     */
    protected LocalDate reminderTime;

    /************************************ Constructors ************************************/

    public ReminderEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
