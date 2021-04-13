package com.sol.domain.solUser.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.filters.SolUserFilters;

public interface SolUserRepository extends CRUDRepository<String, SolUserEntity, SolUserFilters> {
}
