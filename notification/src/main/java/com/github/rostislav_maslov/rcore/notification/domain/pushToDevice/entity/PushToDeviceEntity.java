package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.entity;

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
 * Описание
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class PushToDeviceEntity extends BaseEntity<String> {

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
     * deviceId 
     */
    protected String deviceId;
    /**
     * pushFeedId 
     */
    protected String pushFeedId;
    /**
     * parentId 
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
     * status 
     */
    protected SendingStatus sendingStatus;
    /**
     * readStatus 
     */
    protected ReadStatus readStatus;

    /************************************ Constructors ************************************/

    public PushToDeviceEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
