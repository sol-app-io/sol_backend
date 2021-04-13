package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.entity.PushToDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.filters.PushToDeviceFilters;

public interface PushToDeviceRepository extends CRUDRepository<String, PushToDeviceEntity, PushToDeviceFilters> {
}
