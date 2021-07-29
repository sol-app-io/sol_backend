package com.sol.domain.plan.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.plan.port.PlanRepository;

/**
 * Удаление сущности
 */
public class DeletePlanUseCase extends AbstractDeleteUseCase<String, PlanRepository> {

    public DeletePlanUseCase(PlanRepository repository) {
        super(repository);
    }
}
