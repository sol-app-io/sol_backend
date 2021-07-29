package com.sol.domain.plan.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.plan.entity.PlanEntity;
import com.sol.domain.plan.port.PlanRepository;

/**
 * Поиск сущности
 */
public class FindPlanByIdUseCase extends AbstractFindByIdUseCase<String, PlanEntity, PlanRepository> {

    public FindPlanByIdUseCase(PlanRepository repository) {
        super(repository);
    }
}