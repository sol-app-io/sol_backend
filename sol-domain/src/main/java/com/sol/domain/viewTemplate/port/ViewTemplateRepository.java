package com.sol.domain.viewTemplate.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;

public interface ViewTemplateRepository extends CRUDRepository<String, ViewTemplateEntity, ViewTemplateFilters> {
}
