package com.sol.domain.viewsSort.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.filters.ViewsSortBySolTypeFilters;
import com.sol.domain.viewsSort.port.filters.ViewsSortFilters;

import java.util.List;
import java.util.Optional;

public interface ViewsSortRepository extends CRUDRepository<String, ViewsSortEntity, ViewsSortFilters> {
    void removeByViewId(String viewId);
    Optional<ViewsSortEntity> find(ViewsSortBySolTypeFilters filters);
    List<ViewsSortEntity> findBySolUser(String solUserId);
}
