package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.entity.RCMobileDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.filters.RCMobileDeviceFilters;

public interface RCMobileDeviceRepository extends CRUDRepository<String, RCMobileDeviceEntity, RCMobileDeviceFilters> {
}
