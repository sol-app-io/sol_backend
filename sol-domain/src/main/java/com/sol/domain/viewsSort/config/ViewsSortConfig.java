package com.sol.domain.viewsSort.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.usecases.CreateViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.DeleteViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.FindViewsSortByIdUseCase;
import com.sol.domain.viewsSort.usecases.UpdateViewsSortUseCase;
import com.sol.domain.viewsSort.usecases.FindViewsSortUseCase;

@Accessors(fluent = true)
@Getter
public class ViewsSortConfig {
    private final CreateViewsSortUseCase createViewsSortUseCase;
    private final DeleteViewsSortUseCase deleteViewsSortUseCase;
    private final FindViewsSortByIdUseCase findViewsSortByIdUseCase;
    private final UpdateViewsSortUseCase updateViewsSortUseCase;
    private final FindViewsSortUseCase findViewsSortUseCase;

    public ViewsSortConfig(ViewsSortRepository viewsSortRepository, ViewsSortIdGenerator<?> viewsSortIdGenerator) {
        this.createViewsSortUseCase = new CreateViewsSortUseCase(viewsSortRepository, viewsSortIdGenerator);
        this.deleteViewsSortUseCase = new DeleteViewsSortUseCase(viewsSortRepository);
        this.findViewsSortByIdUseCase = new FindViewsSortByIdUseCase(viewsSortRepository);
        this.updateViewsSortUseCase = new UpdateViewsSortUseCase(viewsSortRepository);
        this.findViewsSortUseCase = new FindViewsSortUseCase(viewsSortRepository);
    }
}
