package com.sol.domain.viewTemplate.port.filters;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ViewTemplateDefaultsFilters extends SearchFilters {
    protected Boolean addByDefault = true;
    protected ViewTemplateEntity.OwnerType ownerType = ViewTemplateEntity.OwnerType.BY_ADMIN;
}
