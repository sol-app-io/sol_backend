package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.entity.PushFeedEntity;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.filters.PushFeedFilters;

public interface PushFeedRepository extends CRUDRepository<String, PushFeedEntity, PushFeedFilters> {
}
