package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.entity;

import com.github.rostislav_maslov.rcore.notification.domain.base.DeviceType;
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
public class PushNotificationConfEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/



    /**
     * Номер 
     */
    protected String title;
    /**
     * Заголовок 
     */
    protected String message;
    /**
     * deviceId 
     */
    protected String deviceId;
    /**
     * pushFeedId 
     */
    protected String pushFeedId;
    /**
     * Заголовок 
     */
    protected String parentId;
    /**
     * userId 
     */
    protected String userId;
    /**
     * deviceType 
     */
    protected DeviceType deviceType;
    /**
     * sendingStatus 
     */
    protected SendingStatus sendingStatus;
    /**
     * readStatus 
     */
    protected ReadStatus readStatus;

    /************************************ Constructors ************************************/

    public PushNotificationConfEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
