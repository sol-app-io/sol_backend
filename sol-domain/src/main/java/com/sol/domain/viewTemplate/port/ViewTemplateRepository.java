package com.sol.domain.viewTemplate.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateAdminsFilters;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateDefaultsFilters;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;

public interface ViewTemplateRepository extends CRUDRepository<String, ViewTemplateEntity, ViewTemplateFilters> {
    SearchResult<ViewTemplateEntity> find(ViewTemplateAdminsFilters filters);
    SearchResult<ViewTemplateEntity> find(ViewTemplateDefaultsFilters filters);
}
