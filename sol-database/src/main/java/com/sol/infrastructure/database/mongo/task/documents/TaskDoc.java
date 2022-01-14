package com.sol.infrastructure.database.mongo.task.documents;

import com.rcore.database.mongo.commons.document.BaseDocument;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.task.entity.DeadlineType;
import com.sol.domain.task.entity.TaskStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Document
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskDoc extends BaseDocument {
    /**
     * ownerId 
     */
    protected ObjectId ownerId;
    /**
     * parentTaskId 
     */
    protected ObjectId parentTaskId;
    /**
     * spaceId 
     */
    protected ObjectId spaceId;
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
    protected Set<ObjectId> viewIds;
    /**
     * planningPoints 
     */
    protected List<ObjectId> planningPoints;
    /**
     * deadline
     */
    protected LocalDateTime deadline;
    protected Boolean deadlineChangeFewTimes = false;
    /**
     * deadline type
     */
    protected DeadlineType deadlineType;
    /**
     * timezone
     */
    protected Integer timezone;
    /**
     * repeatTaskConfId 
     */
    protected ObjectId repeatTaskConfId;
    /**
     * createdFromRepeatTaskId 
     */
    protected ObjectId createdFromRepeatTaskId;
    /**
     * pics 
     */
    protected List<ObjectId> pics;
    /**
     * files 
     */
    protected List<ObjectId> files;
    /**
     * description 
     */
    protected String description;
    /**
     * externalIds 
     */
    protected List<String> externalIds;
    /**
     * slotsMilliseconds
     */
    protected Long slotsMilliseconds;
    /**
     * status 
     */
    protected TaskStatus status;
    /**
     * sortNum
     */
    protected Integer sortNum;

 
}
