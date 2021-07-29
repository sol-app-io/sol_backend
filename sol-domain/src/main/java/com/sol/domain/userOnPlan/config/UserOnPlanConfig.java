package com.sol.domain.userOnPlan.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.userOnPlan.port.UserOnPlanIdGenerator;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;
import com.sol.domain.userOnPlan.usecases.CreateUserOnPlanUseCase;
import com.sol.domain.userOnPlan.usecases.DeleteUserOnPlanUseCase;
import com.sol.domain.userOnPlan.usecases.FindUserOnPlanByIdUseCase;
import com.sol.domain.userOnPlan.usecases.UpdateUserOnPlanUseCase;

@Accessors(fluent = true)
@Getter
public class UserOnPlanConfig {
    private final CreateUserOnPlanUseCase createUserOnPlanUseCase;
    private final DeleteUserOnPlanUseCase deleteUserOnPlanUseCase;
    private final FindUserOnPlanByIdUseCase findUserOnPlanByIdUseCase;
    private final UpdateUserOnPlanUseCase updateUserOnPlanUseCase;

    public UserOnPlanConfig(UserOnPlanRepository userOnPlanEntityRepository, UserOnPlanIdGenerator<?> userOnPlanEntityIdGenerator) {
        this.createUserOnPlanUseCase = new CreateUserOnPlanUseCase(userOnPlanEntityRepository, userOnPlanEntityIdGenerator);
        this.deleteUserOnPlanUseCase = new DeleteUserOnPlanUseCase(userOnPlanEntityRepository);
        this.findUserOnPlanByIdUseCase = new FindUserOnPlanByIdUseCase(userOnPlanEntityRepository);
        this.updateUserOnPlanUseCase = new UpdateUserOnPlanUseCase(userOnPlanEntityRepository);
    }
}
