package com.sol.domain.task.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.base.entity.Icon;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * task
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class TaskEntity extends BaseEntity<String> {
    /**
     * ownerId
     */
    protected String ownerId;
    /**
     * parentTaskId
     */
    protected String parentTaskId;
    /**
     * spaceId
     */
    protected String spaceId;
    /**
     * Заголовок
     */
    protected String title;
    /**
     * icon
     */
    protected Icon icon;
    /**
     * viewIds
     */
    protected List<String> viewIds = new ArrayList<>();
    /**
     * planningPoints
     */
    protected List<String> planningPoints = new ArrayList<>();
    /**
     * deadline
     */
    protected LocalDateTime deadline;
    /**
     * repeatTaskConfId
     */
    protected String repeatTaskConfId;
    /**
     * createdFromRepeatTaskId
     */
    protected String createdFromRepeatTaskId;
    /**
     * pics
     */
    protected List<String> pics = new ArrayList<>();
    /**
     * files
     */
    protected List<String> files = new ArrayList<>();
    /**
     * description
     */
    protected String description = "";
    /**
     * externalIds
     */
    protected List<String> externalIds = new ArrayList<>();
    /**
     * pointWeight
     */
    protected Integer pointWeight = 0;
    /**
     * status
     */
    protected TaskStatus status = TaskStatus.OPEN;

    protected Integer sortNum = 0;


    public TaskEntity(String id) {
        this.id = id;
    }

    public Boolean checkAccessSpace(String spaceId){
        return spaceId.equals(spaceId);
    }

    public Boolean checkAccess(String solUserId){
        return ownerId.equals(solUserId);
    }

}
