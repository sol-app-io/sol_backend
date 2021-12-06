package com.sol.domain.solUser.config;

import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.usecases.*;
import com.sol.domain.space.config.SpaceConfig;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import com.sol.domain.viewUser.config.ViewUserConfig;
import com.sol.domain.viewUser.usecases.InitAllViewsUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class SolUserConfig {
    private final SignUpByEmailSolUserUseCase signUpByEmailSolUserUseCase;
    private final DeleteSolUserUseCase deleteSolUserUseCase;
    private final FindSolUserByIdUseCase findSolUserByIdUseCase;
    private final UpdateSolUserUseCase updateSolUserUseCase;
    private final MeUseCase meUseCase;


    public SolUserConfig(
            SolUserRepository solUserRepository,
            SolUserIdGenerator<?> solUserIdGenerator,
            SpaceConfig spaceConfig,
            ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade credentialServiceFacade,
            ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade roleServiceFacade,
            ViewUserConfig viewUserConfig
    ) {
        this.signUpByEmailSolUserUseCase = new SignUpByEmailSolUserUseCase(
                solUserRepository,
                solUserIdGenerator,
                credentialServiceFacade,
                roleServiceFacade,
                spaceConfig.createSpaceUseCase(),
                viewUserConfig.initAllViewsUseCase()
        );

        this.deleteSolUserUseCase = new DeleteSolUserUseCase(solUserRepository);
        this.findSolUserByIdUseCase = new FindSolUserByIdUseCase(solUserRepository);
        this.updateSolUserUseCase = new UpdateSolUserUseCase(solUserRepository);
        this.meUseCase = new MeUseCase(solUserRepository);
    }
}
