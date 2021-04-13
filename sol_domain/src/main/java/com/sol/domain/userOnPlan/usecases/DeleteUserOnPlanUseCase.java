package com.sol.domain.userOnPlan.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;

/**
 * Удаление сущности
 */
public class DeleteUserOnPlanUseCase extends AbstractDeleteUseCase<String, UserOnPlanRepository> {

    public DeleteUserOnPlanUseCase(UserOnPlanRepository repository) {
        super(repository);
    }
}
