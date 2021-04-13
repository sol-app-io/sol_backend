package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.entity.PushNotificationConfEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.filters.PushNotificationConfFilters;

public interface PushNotificationConfRepository extends CRUDRepository<String, PushNotificationConfEntity, PushNotificationConfFilters> {
}
