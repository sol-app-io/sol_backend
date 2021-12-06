package com.sol.domain.viewUser.port.filters;

import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ViewUserByTemplateFilters extends SearchFilters {
    protected String templateId;
}
