package com.sol.domain.solUser.config;

import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.usecases.CreateCredentialUseCase;
import com.rcore.domain.auth.role.config.RoleConfig;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.usecases.SignUpByEmailSolUserUseCase;
import com.sol.domain.solUser.usecases.DeleteSolUserUseCase;
import com.sol.domain.solUser.usecases.FindSolUserByIdUseCase;
import com.sol.domain.solUser.usecases.UpdateSolUserUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class SolUserConfig {
    private final SignUpByEmailSolUserUseCase signUpByEmailSolUserUseCase;
    private final DeleteSolUserUseCase deleteSolUserUseCase;
    private final FindSolUserByIdUseCase findSolUserByIdUseCase;
    private final UpdateSolUserUseCase updateSolUserUseCase;

    public SolUserConfig(
            SolUserRepository solUserRepository,
            SolUserIdGenerator<?> solUserIdGenerator,
            CredentialRepository credentialRepository,
            CreateCredentialUseCase createCredentialUseCase,
            RoleConfig roleConfig
    ) {
        this.signUpByEmailSolUserUseCase = new SignUpByEmailSolUserUseCase(solUserRepository, solUserIdGenerator, credentialRepository, createCredentialUseCase, roleConfig);
        this.deleteSolUserUseCase = new DeleteSolUserUseCase(solUserRepository);
        this.findSolUserByIdUseCase = new FindSolUserByIdUseCase(solUserRepository);
        this.updateSolUserUseCase = new UpdateSolUserUseCase(solUserRepository);
    }
}
