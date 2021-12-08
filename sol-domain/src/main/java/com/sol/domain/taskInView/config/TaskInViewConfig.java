package com.sol.domain.taskInView.config;

import com.sol.domain.task.port.TaskRepository;
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

    public TaskInViewConfig(TaskInViewRepository taskInViewRepository, TaskInViewIdGenerator<?> taskInViewIdGenerator, TaskRepository taskRepository) {
        this.findTaskInViewByIdUseCase = new FindTaskInViewByIdUseCase(taskInViewRepository, taskInViewIdGenerator, taskRepository);
        this.findTaskInViewUseCase = new FindTaskInViewUseCase(taskInViewRepository);
        this.createTaskInViewUseCase = new CreateTaskInViewUseCase(this.findTaskInViewByIdUseCase, taskInViewRepository);
        this.deleteTaskInViewUseCase = new DeleteTaskInViewUseCase(this.findTaskInViewByIdUseCase, taskInViewRepository, taskRepository);
        this.updateTaskInViewUseCase = new UpdateTaskInViewUseCase(this.findTaskInViewByIdUseCase, taskInViewRepository);
    }
}
