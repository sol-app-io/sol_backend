package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port;

import com.rcore.domain.commons.port.BaseIdGenerator;

//В разных бд или системах могут быть принято разное отношение к формату ID. А бизнес правил это не должно интересовать 
public interface PushFeedIdGenerator<T> extends BaseIdGenerator<T> {
}
