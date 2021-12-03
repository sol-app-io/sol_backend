package com.sol.domain.viewsSort.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.filters.ViewsSortFilters;

public interface ViewsSortRepository extends CRUDRepository<String, ViewsSortEntity, ViewsSortFilters> {
}
