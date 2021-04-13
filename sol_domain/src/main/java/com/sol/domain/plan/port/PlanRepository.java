package com.sol.domain.plan.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.plan.entity.PlanEntity;
import com.sol.domain.plan.port.filters.PlanFilters;

public interface PlanRepository extends CRUDRepository<String, PlanEntity, PlanFilters> {
}
