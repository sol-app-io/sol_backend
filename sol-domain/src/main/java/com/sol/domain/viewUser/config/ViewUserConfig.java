package com.sol.domain.viewUser.config;

import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.usecases.*;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;

@Accessors(fluent = true)
@Getter
public class ViewUserConfig {
    private final CreateViewUserFromTemplateBulkByAdminUseCase createViewUserFromTemplateBulkByAdminUseCase;
    private final CreateViewUserFromTemplateByUserUseCase createViewUserFromTemplateByUserUseCase;
    private final CreateViewUserManuallyByUserUseCase createViewUserManuallyByUserUseCase;
    private final DeleteViewUserUseCase deleteViewUserUseCase;
    private final FindViewUserByIdUseCase findViewUserByIdUseCase;
    private final FindViewUserUseCase findViewUserUseCase;
    private final InitAllViewsUseCase initAllViewsUseCase;
    private final RemoveAllViewByTemplateForAllUserUseCase removeAllViewByTemplateForAllUserUseCase;
    private final UpdateViewsByTemplateUseCase updateViewsByTemplateUseCase;
    private final UpdateViewUserUseCase updateViewUserUseCase;

    public ViewUserConfig(
            ViewUserRepository viewUserRepository,
            ViewUserIdGenerator<?> viewUserIdGenerator,
            TaskInViewRepository taskInViewRepository,
            SolUserRepository solUserRepository,
            ViewTemplateRepository viewTemplateRepository,
            ViewsSortRepository viewsSortRepository,
            BackgroundTaskForViewRepository backgroundTaskForViewRepository) {
        this.createViewUserFromTemplateBulkByAdminUseCase = new CreateViewUserFromTemplateBulkByAdminUseCase(
                solUserRepository,
                viewUserRepository,
                viewUserIdGenerator
        );
        this.createViewUserFromTemplateByUserUseCase = new CreateViewUserFromTemplateByUserUseCase(solUserRepository, viewUserRepository, viewUserIdGenerator);
        this.createViewUserManuallyByUserUseCase = new CreateViewUserManuallyByUserUseCase(viewUserRepository, viewUserIdGenerator);
        this.deleteViewUserUseCase = new DeleteViewUserUseCase(viewUserRepository);
        this.findViewUserByIdUseCase = new FindViewUserByIdUseCase(viewUserRepository);
        this.updateViewUserUseCase = new UpdateViewUserUseCase(viewUserRepository, taskInViewRepository);
        this.findViewUserUseCase = new FindViewUserUseCase(viewUserRepository);
        this.initAllViewsUseCase = new InitAllViewsUseCase(viewTemplateRepository, this.createViewUserFromTemplateByUserUseCase);
        this.removeAllViewByTemplateForAllUserUseCase = new RemoveAllViewByTemplateForAllUserUseCase(viewUserRepository, viewTemplateRepository, viewsSortRepository, taskInViewRepository, backgroundTaskForViewRepository);
        this.updateViewsByTemplateUseCase = new UpdateViewsByTemplateUseCase(viewUserRepository, viewTemplateRepository, taskInViewRepository);
    }
}
