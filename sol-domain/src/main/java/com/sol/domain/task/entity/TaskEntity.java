package com.sol.domain.task.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.base.utils.DateUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    protected Set<String> viewIds = new HashSet<>();
    /**
     * deadline
     */
    protected LocalDateTime deadline;
    /**
     * deadline type
     */
    protected DeadlineType deadlineType;
    protected Boolean deadlineChangeFewTimes = false;
    /**
     * timezone
     */
    protected Integer timezone;
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
     * slotsMilliseconds
     */
    protected Long slotsMilliseconds = 0l;
    /**
     * status
     */
    protected TaskStatus status = TaskStatus.OPEN;

    protected Integer sortNum = 0;


    public TaskEntity(String id) {
        this.id = id;
    }

    public Boolean checkAccessSpace(String spaceId) {
        return spaceId.equals(spaceId);
    }

    public Boolean checkAccess(String solUserId) {
        return ownerId.equals(solUserId);
    }

    public Long getDeadlineMill(){
        return DateUtils.convert(getDeadline(), getTimezone());
    }

    public String getFullTime(){
        return (
                (this.icon != null && this.icon.getData() != null) ?
                        (this.icon.getData() + " ") : ""
                ) + title;
    }

    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Data
    public static class TaskWithChildEntity extends TaskEntity {
        protected List<TaskWithChildEntity> child = new ArrayList<>();

        public static TaskWithChildEntity of(TaskEntity taskEntity) {
            return TaskWithChildEntity.builder()
                    .id(taskEntity.id)
                    .createdAt(taskEntity.createdAt)
                    .updatedAt(taskEntity.updatedAt)
                    .ownerId(taskEntity.ownerId)
                    .parentTaskId(taskEntity.parentTaskId)
                    .spaceId(taskEntity.spaceId)
                    .title(taskEntity.title)
                    .icon(taskEntity.icon)
                    .viewIds(taskEntity.viewIds)
                    .deadline(taskEntity.deadline)
                    .repeatTaskConfId(taskEntity.repeatTaskConfId)
                    .createdFromRepeatTaskId(taskEntity.createdFromRepeatTaskId)
                    .pics(taskEntity.pics)
                    .files(taskEntity.files)
                    .description(taskEntity.description)
                    .externalIds(taskEntity.externalIds)
                    .slotsMilliseconds(taskEntity.slotsMilliseconds)
                    .status(taskEntity.status)
                    .sortNum(taskEntity.sortNum)
                    .child(new ArrayList<>())
                    .build();

        }
    }

}
