package com.sol.domain.backgroundTaskForView.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Background task
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class BackgroundTaskForViewEntity extends BaseEntity<String> {
    public enum Status{
        NEW,IN_PROCESS,SUCCESS,ERROR
    }

    /**
     * Task 
     */
    protected String taskId;
    /**
     * status 
     */
    protected Status status = Status.NEW;
    /**
     * log 
     */
    protected String log = "";


    public BackgroundTaskForViewEntity(String id) {
        this.id = id;
    }

}
