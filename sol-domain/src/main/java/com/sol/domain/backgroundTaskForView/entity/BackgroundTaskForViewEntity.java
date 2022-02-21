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
    public enum Type{
        TASK, VIEW
    }

    /**
     * Task 
     */
    protected String taskId;

    /**
     * user
     */
    protected String userViewId;
    /**
     * status 
     */
    protected Status status = Status.NEW;

    protected Type type = Type.TASK;
    /**
     * log 
     */
    protected String log = "";


    public BackgroundTaskForViewEntity(String id) {
        this.id = id;
    }

}
