package com.sol.domain.slot.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.port.filters.SlotFilters;

public interface SlotRepository extends CRUDRepository<String, SlotEntity, SlotFilters> {
}
