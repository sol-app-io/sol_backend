package com.sol.domain.viewsSort.config;

import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.usecases.FindViewsSortByUserIdUseCase;
import com.sol.domain.viewsSort.usecases.FindViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.UpdateViewsSortUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class ViewsSortConfig {
    private final FindViewsSortByUserIdUseCase findViewsSortByUserIdUseCase;
    private final UpdateViewsSortUseCase updateViewsSortUseCase;
    private final FindViewsSortUseCase findViewsSortUseCase;

    public ViewsSortConfig(ViewsSortRepository viewsSortRepository, ViewUserRepository viewUserRepository, ViewsSortIdGenerator viewsSortIdGenerator) {
        this.findViewsSortByUserIdUseCase = new FindViewsSortByUserIdUseCase(viewsSortRepository, viewUserRepository, viewsSortIdGenerator);
        this.updateViewsSortUseCase = new UpdateViewsSortUseCase(viewsSortRepository, viewUserRepository, viewsSortIdGenerator);
        this.findViewsSortUseCase = new FindViewsSortUseCase(viewsSortRepository);
    }
}
