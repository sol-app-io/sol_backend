package com.sol.domain.viewsSort.port.filters;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ViewsSortBySolTypeFilters extends SearchFilters {
    protected String solUserId;
    protected ViewsSortEntity.Type type;
}
