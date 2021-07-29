package com.sol.domain.view.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.view.entity.ViewEntity;
import com.sol.domain.view.port.filters.ViewFilters;

public interface ViewRepository extends CRUDRepository<String, ViewEntity, ViewFilters> {
}
