package com.sol.domain.repeatTaskConf.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import com.sol.domain.repeatTaskConf.port.filters.RepeatTaskConfFilters;

public interface RepeatTaskConfRepository extends CRUDRepository<String, RepeatTaskConfEntity, RepeatTaskConfFilters> {
}
