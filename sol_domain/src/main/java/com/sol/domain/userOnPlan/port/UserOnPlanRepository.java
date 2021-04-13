package com.sol.domain.userOnPlan.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import com.sol.domain.userOnPlan.port.filters.UserOnPlanFilters;

public interface UserOnPlanRepository extends CRUDRepository<String, UserOnPlanEntity, UserOnPlanFilters> {
}
