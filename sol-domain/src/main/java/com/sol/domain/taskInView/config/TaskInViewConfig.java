package com.sol.domain.taskInView.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.DeleteTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.FindTaskInViewByIdUseCase;
import com.sol.domain.taskInView.usecases.UpdateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.FindTaskInViewUseCase;

@Accessors(fluent = true)
@Getter
public class TaskInViewConfig {
    private final CreateTaskInViewUseCase createTaskInViewUseCase;
    private final DeleteTaskInViewUseCase deleteTaskInViewUseCase;
    private final FindTaskInViewByIdUseCase findTaskInViewByIdUseCase;
    private final UpdateTaskInViewUseCase updateTaskInViewUseCase;
    private final FindTaskInViewUseCase findTaskInViewUseCase;

    public TaskInViewConfig(TaskInViewRepository taskInViewRepository, TaskInViewIdGenerator<?> taskInViewIdGenerator) {
        this.createTaskInViewUseCase = new CreateTaskInViewUseCase(taskInViewRepository, taskInViewIdGenerator);
        this.deleteTaskInViewUseCase = new DeleteTaskInViewUseCase(taskInViewRepository);
        this.findTaskInViewByIdUseCase = new FindTaskInViewByIdUseCase(taskInViewRepository);
        this.updateTaskInViewUseCase = new UpdateTaskInViewUseCase(taskInViewRepository);
        this.findTaskInViewUseCase = new FindTaskInViewUseCase(taskInViewRepository);
    }
}
