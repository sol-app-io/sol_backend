package com.sol.domain.space.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.port.filters.SpaceFilters;

import java.util.List;

public interface SpaceRepository extends CRUDRepository<String, SpaceEntity, SpaceFilters> {
    Long countSpaces(String ownerId);
    List<SpaceEntity> findByOwner(String ownerId);
}
