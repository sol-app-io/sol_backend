package com.sol.domain.view.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.view.port.ViewIdGenerator;
import com.sol.domain.view.port.ViewRepository;
import com.sol.domain.view.usecases.CreateViewUseCase;
import com.sol.domain.view.usecases.DeleteViewUseCase;
import com.sol.domain.view.usecases.FindViewByIdUseCase;
import com.sol.domain.view.usecases.UpdateViewUseCase;

@Accessors(fluent = true)
@Getter
public class ViewConfig {
    private final CreateViewUseCase createViewUseCase;
    private final DeleteViewUseCase deleteViewUseCase;
    private final FindViewByIdUseCase findViewByIdUseCase;
    private final UpdateViewUseCase updateViewUseCase;

    public ViewConfig(ViewRepository viewRepository, ViewIdGenerator<?> viewIdGenerator) {
        this.createViewUseCase = new CreateViewUseCase(viewRepository, viewIdGenerator);
        this.deleteViewUseCase = new DeleteViewUseCase(viewRepository);
        this.findViewByIdUseCase = new FindViewByIdUseCase(viewRepository);
        this.updateViewUseCase = new UpdateViewUseCase(viewRepository);
    }
}
