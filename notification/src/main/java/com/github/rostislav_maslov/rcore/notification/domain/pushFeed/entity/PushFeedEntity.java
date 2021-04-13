package com.github.rostislav_maslov.rcore.notification.domain.pushFeed.entity;

import com.github.rostislav_maslov.rcore.notification.domain.base.Priority;
import com.github.rostislav_maslov.rcore.notification.domain.base.ReadStatus;
import com.github.rostislav_maslov.rcore.notification.domain.base.SendingStatus;
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
public class PushFeedEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/



    /**
     * title 
     */
    protected String title;
    /**
     * message 
     */
    protected String message;
    /**
     * parentId 
     */
    protected String parentId;
    /**
     * userId 
     */
    protected String userId;
    /**
     * SendingStatus 
     */
    protected SendingStatus sendingStatus;
    /**
     * readStatus 
     */
    protected ReadStatus readStatus;
    /**
     * priority 
     */
    protected Priority priority;
    /**
     * error 
     */
    protected String error;

    /************************************ Constructors ************************************/

    public PushFeedEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
