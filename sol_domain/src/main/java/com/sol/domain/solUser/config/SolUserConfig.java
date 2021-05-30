package com.sol.domain.solUser.config;

import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.port.impl.PasswordCryptographerImpl;
import com.rcore.domain.auth.credential.usecases.CreateCredentialUseCase;
import com.rcore.domain.auth.role.config.RoleConfig;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.usecases.*;
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
            CredentialRepository credentialRepository,
            CreateCredentialUseCase createCredentialUseCase,
            RoleConfig roleConfig
    ) {
        this.signUpByEmailSolUserUseCase = new SignUpByEmailSolUserUseCase(
                solUserRepository, solUserIdGenerator, credentialRepository, createCredentialUseCase, roleConfig, new PasswordCryptographerImpl()
        );
        this.deleteSolUserUseCase = new DeleteSolUserUseCase(solUserRepository);
        this.findSolUserByIdUseCase = new FindSolUserByIdUseCase(solUserRepository);
        this.updateSolUserUseCase = new UpdateSolUserUseCase(solUserRepository);
        this.meUseCase = new MeUseCase(solUserRepository);
    }
}
