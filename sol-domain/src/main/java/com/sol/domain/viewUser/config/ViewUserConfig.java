package com.sol.domain.viewUser.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.usecases.CreateViewUserUseCase;
import com.sol.domain.viewUser.usecases.DeleteViewUserUseCase;
import com.sol.domain.viewUser.usecases.FindViewUserByIdUseCase;
import com.sol.domain.viewUser.usecases.UpdateViewUserUseCase;
import com.sol.domain.viewUser.usecases.FindViewUserUseCase;

@Accessors(fluent = true)
@Getter
public class ViewUserConfig {
    private final CreateViewUserUseCase createViewUserUseCase;
    private final DeleteViewUserUseCase deleteViewUserUseCase;
    private final FindViewUserByIdUseCase findViewUserByIdUseCase;
    private final UpdateViewUserUseCase updateViewUserUseCase;
    private final FindViewUserUseCase findViewUserUseCase;

    public ViewUserConfig(ViewUserRepository viewUserRepository, ViewUserIdGenerator<?> viewUserIdGenerator) {
        this.createViewUserUseCase = new CreateViewUserUseCase(viewUserRepository, viewUserIdGenerator);
        this.deleteViewUserUseCase = new DeleteViewUserUseCase(viewUserRepository);
        this.findViewUserByIdUseCase = new FindViewUserByIdUseCase(viewUserRepository);
        this.updateViewUserUseCase = new UpdateViewUserUseCase(viewUserRepository);
        this.findViewUserUseCase = new FindViewUserUseCase(viewUserRepository);
    }
}
