package com.sol.domain.repeatTaskConf.port;

import com.rcore.domain.commons.port.BaseIdGenerator;

//В разных бд или системах могут быть принято разное отношение к формату ID. А бизнес правил это не должно интересовать 
public interface RepeatTaskConfIdGenerator<T> extends BaseIdGenerator<T> {
}
