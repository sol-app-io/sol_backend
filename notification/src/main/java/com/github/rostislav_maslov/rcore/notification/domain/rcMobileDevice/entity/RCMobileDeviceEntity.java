package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.entity;

import com.github.rostislav_maslov.rcore.notification.domain.base.DeviceType;
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
public class RCMobileDeviceEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/



    /**
     * ownerId 
     */
    protected String ownerId;
    /**
     * deviceToken 
     */
    protected String deviceToken;
    /**
     * deviceType 
     */
    protected DeviceType deviceType;

    /************************************ Constructors ************************************/

    public RCMobileDeviceEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
