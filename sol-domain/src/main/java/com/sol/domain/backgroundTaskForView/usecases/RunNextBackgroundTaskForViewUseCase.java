package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.config.TaskInViewConfig;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.taskInView.usecases.DeleteTaskInViewUseCase;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.exceptions.ViewsSortNotFoundException;
import lombok.*;
import com.sol.domain.backgroundTaskForView.entity.BackgroundTaskForViewEntity;
import com.sol.domain.backgroundTaskForView.exceptions.BackgroundTaskForViewNotFoundException;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class RunNextBackgroundTaskForViewUseCase extends UseCase<RunNextBackgroundTaskForViewUseCase.InputValues, VoidOutputValues> {

    private final BackgroundTaskForViewRepository backgroundTaskForViewRepository;
    private final TaskRepository taskRepository;
    private final ViewUserRepository viewUserRepository;
    private final CreateTaskInViewUseCase createTaskInViewUseCase;
    private final DeleteTaskInViewUseCase deleteTaskInViewUseCase;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        Optional<BackgroundTaskForViewEntity> optionalBackgroundTaskForViewEntity =
                inputValues.backgroundId == null ?
                        backgroundTaskForViewRepository.findNext() :
                        backgroundTaskForViewRepository.findById(inputValues.backgroundId);


        if (optionalBackgroundTaskForViewEntity.isEmpty()) return new VoidOutputValues();

        BackgroundTaskForViewEntity background = optionalBackgroundTaskForViewEntity.get();
        try {
            if (background.getType().equals(BackgroundTaskForViewEntity.Type.TASK)) {
                executeTask(background);
            }

            if (background.getType().equals(BackgroundTaskForViewEntity.Type.VIEW)) {
                executeView(background);
            }

        } catch (Exception e) {
            background.setStatus(BackgroundTaskForViewEntity.Status.ERROR);
            background.setLog(e.getMessage());
            backgroundTaskForViewRepository.save(background);
            return new VoidOutputValues();
        }

        //TODO: их удалять бы - что бы не мешались
        background.setStatus(BackgroundTaskForViewEntity.Status.SUCCESS);
        backgroundTaskForViewRepository.save(background);
        return new VoidOutputValues();
    }

    private void executeTask(BackgroundTaskForViewEntity background) {
        TaskEntity taskEntity = taskRepository.findById(background.getTaskId()).orElseThrow(TaskNotFoundException::new);
        List<ViewUserEntity> views = viewUserRepository.find(taskEntity.getOwnerId());
        for (ViewUserEntity view : views) {
            execute(view, taskEntity);
        }

    }

    private void executeView(BackgroundTaskForViewEntity background) {
        ViewUserEntity view = viewUserRepository.findById(background.getUserViewId()).orElseThrow(ViewsSortNotFoundException::new);

        if (view.getView().getViewType().equals(View.ViewType.CUSTOM)) return;
        if (view.getView().getAddedType().equals(View.AddedType.MANUALLY)) return;

        Boolean hasClosed = view.getView().getParams()
                .stream()
                .anyMatch(params -> params.getType().equals(View.Params.Type.CLOSED) && params.getValueBool());

        if (hasClosed) {
            Long limit = 100l, skip = 0l;
            while (true) {
                List<TaskEntity> taskEntities = taskRepository.findByUserId(view.getOwnerId(), skip, limit.intValue());
                if (taskEntities.size() == 0) break;
                skip += limit;

                for (TaskEntity taskEntity : taskEntities) {
                    execute(view, taskEntity);
                }
            }
        } else {
            List<TaskEntity> taskEntities = taskRepository.findByUserId(view.getOwnerId());
            for (TaskEntity taskEntity : taskEntities) {
                execute(view, taskEntity);
            }
        }
    }

    private void execute(ViewUserEntity view, TaskEntity taskEntity) {
        if (view.getView().getViewType().equals(View.ViewType.CUSTOM)) {
            return;
        }

        View.CheckTaskType checkTaskType = view.getView().checkTask(taskEntity);

        if (checkTaskType.equals(View.CheckTaskType.NEED_ADD_AUTO)) {
            if (taskEntity.getViewIds().contains(view.getId()) == false) {
                createTaskInViewUseCase.execute(CreateTaskInViewUseCase.InputValues.of(view.getId(), taskEntity.getId(), null));
                view.setHasTaskAdded(true);
            }
        } else if (checkTaskType.equals(View.CheckTaskType.NEED_ADD_MANUALLY)) {
            if (taskEntity.getViewIds().contains(view.getId()) == false) {
                view.setHasNewTaskToAdd(true);
                taskEntity.getSuggestForViewIds().add(view.getId());
                taskRepository.save(taskEntity);
            }
        } else if (checkTaskType.equals(View.CheckTaskType.NOT_IN_VIEW)) {
            taskEntity.getViewIds().remove(view.getId());
            taskEntity.getSuggestForViewIds().remove(view.getId());
            taskRepository.save(taskEntity);

            deleteTaskInViewUseCase.execute(DeleteTaskInViewUseCase.InputValues.of(view.getId(), taskEntity.getId()));
        }

        viewUserRepository.save(view);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        private String backgroundId;
    }
}
