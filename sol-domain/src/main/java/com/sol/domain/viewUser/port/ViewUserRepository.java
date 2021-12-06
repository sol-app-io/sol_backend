package com.sol.domain.viewUser.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import com.sol.domain.viewUser.port.filters.ViewUserFilters;

import java.util.List;
import java.util.Optional;

public interface ViewUserRepository extends CRUDRepository<String, ViewUserEntity, ViewUserFilters> {
    SearchResult<ViewUserEntity> find(ViewUserByTemplateFilters filters);
    List<ViewUserEntity> find(String solUserId);
    Optional<ViewUserEntity> findByTemplateAndUser(String solUserId, String templateId);
}
