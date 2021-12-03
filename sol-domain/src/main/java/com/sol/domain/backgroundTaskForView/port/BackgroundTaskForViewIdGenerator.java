package com.sol.domain.backgroundTaskForView.port;

import com.rcore.domain.commons.port.BaseIdGenerator;

//В разных бд или системах могут быть принято разное отношение к формату ID. А бизнес правил это не должно интересовать 
public interface BackgroundTaskForViewIdGenerator<T> extends BaseIdGenerator<T> {
}
