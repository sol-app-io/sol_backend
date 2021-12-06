package com.sol.domain.backgroundTaskForView.config;

import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.backgroundTaskForView.usecases.FindBackgroundTaskForViewByIdUseCase;
import com.sol.domain.backgroundTaskForView.usecases.FindBackgroundTaskForViewUseCase;
import com.sol.domain.backgroundTaskForView.usecases.RunNextBackgroundTaskForViewUseCase;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class BackgroundTaskForViewConfig {
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;
    private final FindBackgroundTaskForViewByIdUseCase findBackgroundTaskForViewByIdUseCase;
    private final RunNextBackgroundTaskForViewUseCase runNextBackgroundTaskForViewUseCase;
    private final FindBackgroundTaskForViewUseCase findBackgroundTaskForViewUseCase;

    public BackgroundTaskForViewConfig(
            BackgroundTaskForViewRepository backgroundTaskForViewRepository,
            BackgroundTaskForViewIdGenerator<?> backgroundTaskForViewIdGenerator,
            TaskRepository taskRepository,
            ViewUserRepository viewUserRepository,
            CreateTaskInViewUseCase createTaskInViewUseCase) {
        this.createBackgroundTaskForViewUseCase = new CreateBackgroundTaskForViewUseCase(backgroundTaskForViewRepository, backgroundTaskForViewIdGenerator);
        this.findBackgroundTaskForViewByIdUseCase = new FindBackgroundTaskForViewByIdUseCase(backgroundTaskForViewRepository);
        this.runNextBackgroundTaskForViewUseCase = new RunNextBackgroundTaskForViewUseCase(backgroundTaskForViewRepository, taskRepository, viewUserRepository, createTaskInViewUseCase);
        this.findBackgroundTaskForViewUseCase = new FindBackgroundTaskForViewUseCase(backgroundTaskForViewRepository);
    }
}
