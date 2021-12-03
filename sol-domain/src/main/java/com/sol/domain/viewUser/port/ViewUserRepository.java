package com.sol.domain.viewUser.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.filters.ViewUserFilters;

public interface ViewUserRepository extends CRUDRepository<String, ViewUserEntity, ViewUserFilters> {
}
