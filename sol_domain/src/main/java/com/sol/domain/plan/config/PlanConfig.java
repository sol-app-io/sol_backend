package com.sol.domain.plan.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.plan.port.PlanIdGenerator;
import com.sol.domain.plan.port.PlanRepository;
import com.sol.domain.plan.usecases.CreatePlanUseCase;
import com.sol.domain.plan.usecases.DeletePlanUseCase;
import com.sol.domain.plan.usecases.FindPlanByIdUseCase;
import com.sol.domain.plan.usecases.UpdatePlanUseCase;

@Accessors(fluent = true)
@Getter
public class PlanConfig {
    private final CreatePlanUseCase createPlanUseCase;
    private final DeletePlanUseCase deletePlanUseCase;
    private final FindPlanByIdUseCase findPlanByIdUseCase;
    private final UpdatePlanUseCase updatePlanUseCase;

    public PlanConfig(PlanRepository planRepository, PlanIdGenerator<?> planIdGenerator) {
        this.createPlanUseCase = new CreatePlanUseCase(planRepository, planIdGenerator);
        this.deletePlanUseCase = new DeletePlanUseCase(planRepository);
        this.findPlanByIdUseCase = new FindPlanByIdUseCase(planRepository);
        this.updatePlanUseCase = new UpdatePlanUseCase(planRepository);
    }
}
