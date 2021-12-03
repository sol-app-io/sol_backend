package com.sol.domain.viewTemplate.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewTemplate.usecases.CreateViewTemplateUseCase;
import com.sol.domain.viewTemplate.usecases.DeleteViewTemplateUseCase;
import com.sol.domain.viewTemplate.usecases.FindViewTemplateByIdUseCase;
import com.sol.domain.viewTemplate.usecases.UpdateViewTemplateUseCase;
import com.sol.domain.viewTemplate.usecases.FindViewTemplateUseCase;

@Accessors(fluent = true)
@Getter
public class ViewTemplateConfig {
    private final CreateViewTemplateUseCase createViewTemplateUseCase;
    private final DeleteViewTemplateUseCase deleteViewTemplateUseCase;
    private final FindViewTemplateByIdUseCase findViewTemplateByIdUseCase;
    private final UpdateViewTemplateUseCase updateViewTemplateUseCase;
    private final FindViewTemplateUseCase findViewTemplateUseCase;

    public ViewTemplateConfig(ViewTemplateRepository viewTemplateRepository, ViewTemplateIdGenerator<?> viewTemplateIdGenerator) {
        this.createViewTemplateUseCase = new CreateViewTemplateUseCase(viewTemplateRepository, viewTemplateIdGenerator);
        this.deleteViewTemplateUseCase = new DeleteViewTemplateUseCase(viewTemplateRepository);
        this.findViewTemplateByIdUseCase = new FindViewTemplateByIdUseCase(viewTemplateRepository);
        this.updateViewTemplateUseCase = new UpdateViewTemplateUseCase(viewTemplateRepository);
        this.findViewTemplateUseCase = new FindViewTemplateUseCase(viewTemplateRepository);
    }
}
