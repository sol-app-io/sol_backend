package com.sol.domain.category.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.category.entity.CategoryEntity;
import com.sol.domain.category.port.filters.CategoryFilters;

public interface CategoryRepository extends CRUDRepository<String, CategoryEntity, CategoryFilters> {
}
