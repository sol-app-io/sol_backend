package com.sol.domain.backgroundTaskForView.config;

import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.backgroundTaskForView.usecases.*;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.DeleteTaskInViewUseCase;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class BackgroundTaskForViewConfig {
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;
    private final CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase;
    private final FindBackgroundTaskForViewByIdUseCase findBackgroundTaskForViewByIdUseCase;
    private final RunNextBackgroundTaskForViewUseCase runNextBackgroundTaskForViewUseCase;
    private final FindBackgroundTaskForViewUseCase findBackgroundTaskForViewUseCase;

    public BackgroundTaskForViewConfig(
            BackgroundTaskForViewRepository backgroundTaskForViewRepository,
            BackgroundTaskForViewIdGenerator<?> backgroundTaskForViewIdGenerator,
            TaskRepository taskRepository,
            ViewUserRepository viewUserRepository,
            CreateTaskInViewUseCase createTaskInViewUseCase,
             DeleteTaskInViewUseCase deleteTaskInViewUseCase) {
        this.createBackgroundViewForViewUseCase = new CreateBackgroundViewForViewUseCase(backgroundTaskForViewRepository, backgroundTaskForViewIdGenerator);
        this.findBackgroundTaskForViewByIdUseCase = new FindBackgroundTaskForViewByIdUseCase(backgroundTaskForViewRepository);
        this.runNextBackgroundTaskForViewUseCase = new RunNextBackgroundTaskForViewUseCase(backgroundTaskForViewRepository, taskRepository, viewUserRepository, createTaskInViewUseCase, deleteTaskInViewUseCase);
        this.findBackgroundTaskForViewUseCase = new FindBackgroundTaskForViewUseCase(backgroundTaskForViewRepository);
        this.createBackgroundTaskForViewUseCase = new CreateBackgroundTaskForViewUseCase(backgroundTaskForViewRepository, backgroundTaskForViewIdGenerator, this.runNextBackgroundTaskForViewUseCase);
    }
}
