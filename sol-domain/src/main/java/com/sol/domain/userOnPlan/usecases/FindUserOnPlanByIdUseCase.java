package com.sol.domain.userOnPlan.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;

/**
 * Поиск сущности
 */
public class FindUserOnPlanByIdUseCase extends AbstractFindByIdUseCase<String, UserOnPlanEntity, UserOnPlanRepository> {

    public FindUserOnPlanByIdUseCase(UserOnPlanRepository repository) {
        super(repository);
    }
}