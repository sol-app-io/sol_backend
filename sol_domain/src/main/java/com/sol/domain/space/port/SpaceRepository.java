package com.sol.domain.space.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.filters.SpaceFilters;

public interface SpaceRepository extends CRUDRepository<String, SpaceEntity, SpaceFilters> {
}
