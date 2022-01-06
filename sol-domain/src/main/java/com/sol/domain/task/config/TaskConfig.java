package com.sol.domain.task.config;

import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.space.usecases.UpdateTaskCountInSpaceUseCase;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.space.usecases.FindInboxSpaceByOwnerIdUseCase;
import com.sol.domain.space.usecases.FindSpaceByIdUseCase;
import com.sol.domain.task.usecases.*;
import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.domain.task.port.TaskRepository;

@Accessors(fluent = true)
@Getter
public class TaskConfig {
    private final CreateTaskUseCase createTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final EditTitleIconTaskUseCase editTitleIconTaskUseCase;
    private final FindTaskUseCase findTaskUseCase;
    private final FindTaskBySpaceIdUseCase findTaskBySpaceIdUseCase;
    private final FindTaskByParentUseCase findTaskByParentUseCase;
    private final MakeTaskDoneUseCase makeTaskDoneUseCase;
    private final MakeTaskOpenUseCase makeTaskOpenUseCase;
    private final ChangeSortTasksUseCase changeSortTasksUseCase;
    private final RecalcSlotsTimeForTaskUseCase recalcSlotsTimeForTaskUseCase;
    private final UpdateTaskCountInSpaceUseCase updateTaskCountInSpaceUseCase;
    private final FindTaskByUserIdUseCase findTaskByUserIdUseCase;

    public TaskConfig(
            TaskRepository taskRepository,
            TaskIdGenerator<?> taskIdGenerator,
            MeUseCase meUseCase,
            FindSpaceByIdUseCase findSpaceByIdUseCase,
            FindInboxSpaceByOwnerIdUseCase findInboxSpaceByOwnerIdUseCase,
            SlotRepository slotRepository,
            UpdateTaskCountInSpaceUseCase updateTaskCountInSpaceUseCase) {
        this.findTaskByIdUseCase = new FindTaskByIdUseCase(taskRepository);
        this.createTaskUseCase = new CreateTaskUseCase(taskRepository, taskIdGenerator, meUseCase, findSpaceByIdUseCase, findTaskByIdUseCase, findInboxSpaceByOwnerIdUseCase, updateTaskCountInSpaceUseCase);
        this.deleteTaskUseCase = new DeleteTaskUseCase(taskRepository);
        this.updateTaskUseCase = new UpdateTaskUseCase(taskRepository);
        this.editTitleIconTaskUseCase = new EditTitleIconTaskUseCase(taskRepository);
        this.findTaskUseCase = new FindTaskUseCase(taskRepository);
        this.findTaskBySpaceIdUseCase = new FindTaskBySpaceIdUseCase(taskRepository);
        this.findTaskByParentUseCase = new FindTaskByParentUseCase(taskRepository);
        this.makeTaskDoneUseCase = new MakeTaskDoneUseCase(taskRepository, updateTaskCountInSpaceUseCase);
        this.makeTaskOpenUseCase = new MakeTaskOpenUseCase(taskRepository, updateTaskCountInSpaceUseCase);
        this.changeSortTasksUseCase = new ChangeSortTasksUseCase(taskRepository);
        this.recalcSlotsTimeForTaskUseCase = new RecalcSlotsTimeForTaskUseCase(taskRepository,slotRepository);
        this.updateTaskCountInSpaceUseCase = updateTaskCountInSpaceUseCase;
        this.findTaskByUserIdUseCase = new FindTaskByUserIdUseCase(taskRepository);
    }
}
