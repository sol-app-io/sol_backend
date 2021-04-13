package com.sol.domain.solUser.config;

import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.usecases.CreateSolUserUseCase;
import com.sol.domain.solUser.usecases.DeleteSolUserUseCase;
import com.sol.domain.solUser.usecases.FindSolUserByIdUseCase;
import com.sol.domain.solUser.usecases.UpdateSolUserUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class SolUserConfig {
    private final CreateSolUserUseCase createSolUserUseCase;
    private final DeleteSolUserUseCase deleteSolUserUseCase;
    private final FindSolUserByIdUseCase findSolUserByIdUseCase;
    private final UpdateSolUserUseCase updateSolUserUseCase;

    public SolUserConfig(SolUserRepository solUserRepository, SolUserIdGenerator<?> solUserIdGenerator) {
        this.createSolUserUseCase = new CreateSolUserUseCase(solUserRepository, solUserIdGenerator);
        this.deleteSolUserUseCase = new DeleteSolUserUseCase(solUserRepository);
        this.findSolUserByIdUseCase = new FindSolUserByIdUseCase(solUserRepository);
        this.updateSolUserUseCase = new UpdateSolUserUseCase(solUserRepository);
    }
}
