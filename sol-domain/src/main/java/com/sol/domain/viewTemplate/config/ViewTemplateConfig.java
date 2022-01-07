package com.sol.domain.viewTemplate.config;

import com.sol.domain.viewTemplate.usecases.*;
import com.sol.domain.viewUser.usecases.RemoveAllViewByTemplateForAllUserUseCase;
import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

@Accessors(fluent = true)
@Getter
public class ViewTemplateConfig {
    private final CreateViewTemplateByAdminUseCase createViewTemplateByAdminUseCase;
    private final DeleteViewTemplateUseCase deleteViewTemplateUseCase;
    private final FindViewTemplateByIdUseCase findViewTemplateByIdUseCase;
    private final UpdateViewInTemplateByAdminUseCase updateViewInTemplateByAdminUseCase;
    private final FindViewTemplateForAdminUseCase findViewTemplateForAdminUseCase;
    private final FindViewTemplateDefaultsUseCase findViewTemplateDefaultsUseCase;
    private final FindViewTemplatesUseCase findViewTemplatesUseCase;

    /**
     * Params
     */
    private final AddParamsToViewInTemplateUseCase addParamsToViewInTemplateUseCase;
    private final EditParamsToViewInTemplateUseCase editParamsToViewInTemplateUseCase;
    private final DeleteParamsToViewInTemplateUseCase deleteParamsToViewInTemplateUseCase;

    public ViewTemplateConfig(
            ViewTemplateRepository viewTemplateRepository,
            ViewTemplateIdGenerator<?> viewTemplateIdGenerator,
            RemoveAllViewByTemplateForAllUserUseCase removeAllViewByTemplateForAllUserUseCase) {
        this.createViewTemplateByAdminUseCase = new CreateViewTemplateByAdminUseCase(viewTemplateRepository, viewTemplateIdGenerator);
        this.deleteViewTemplateUseCase = new DeleteViewTemplateUseCase(viewTemplateRepository, removeAllViewByTemplateForAllUserUseCase);
        this.findViewTemplateByIdUseCase = new FindViewTemplateByIdUseCase(viewTemplateRepository);
        this.updateViewInTemplateByAdminUseCase = new UpdateViewInTemplateByAdminUseCase(viewTemplateRepository);
        this.findViewTemplateForAdminUseCase = new FindViewTemplateForAdminUseCase(viewTemplateRepository);
        this.findViewTemplatesUseCase = new FindViewTemplatesUseCase(viewTemplateRepository);

        /**
         * Params
         */
        this.addParamsToViewInTemplateUseCase = new AddParamsToViewInTemplateUseCase(viewTemplateRepository,viewTemplateIdGenerator);
        this.editParamsToViewInTemplateUseCase = new EditParamsToViewInTemplateUseCase(viewTemplateRepository);
        this.deleteParamsToViewInTemplateUseCase = new DeleteParamsToViewInTemplateUseCase(viewTemplateRepository);
        this.findViewTemplateDefaultsUseCase = new FindViewTemplateDefaultsUseCase(viewTemplateRepository);
    }
}
