package com.sol.domain.solUser.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.filters.SolUserFilters;

import java.util.Optional;

public interface SolUserRepository extends CRUDRepository<String, SolUserEntity, SolUserFilters> {
    public Optional<SolUserEntity> findByCredential(String credentialId);
}
