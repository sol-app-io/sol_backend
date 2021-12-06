package com.sol.domain.backgroundTaskForView.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.exceptions.TaskNotFoundException;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.taskInView.usecases.CreateTaskInViewUseCase;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
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

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        Optional<BackgroundTaskForViewEntity> optionalBackgroundTaskForViewEntity = backgroundTaskForViewRepository.findNext();

        if (optionalBackgroundTaskForViewEntity.isEmpty()) return new VoidOutputValues();

        BackgroundTaskForViewEntity background = optionalBackgroundTaskForViewEntity.get();

        try {
            TaskEntity taskEntity = taskRepository.findById(background.getTaskId()).orElseThrow(TaskNotFoundException::new);
            List<ViewUserEntity> views = viewUserRepository.find(taskEntity.getOwnerId());
            for (ViewUserEntity view : views) {

                View.CheckTaskType checkTaskType = view.getView().checkTask(taskEntity);

                if (checkTaskType.equals(View.CheckTaskType.NEED_ADD_AUTO)) {
                    if(taskEntity.getViewIds().contains(view.getId())){
                        //Делаем ничего
                    }else{
                        createTaskInViewUseCase.execute(CreateTaskInViewUseCase.InputValues.of(view.getId(), taskEntity.getId(), null));
                        view.setHasTaskAdded(true);
                    }
                }
                if (checkTaskType.equals(View.CheckTaskType.NEED_ADD_MANUALLY)) {
                    view.setHasNewTaskToAdd(true);
                }

                viewUserRepository.save(view);
            }
        } catch (Exception e) {
            background.setStatus(BackgroundTaskForViewEntity.Status.ERROR);
            background.setLog(e.getMessage());
        }

        //TODO: их удалять бы - что бы не мешались
        background.setStatus(BackgroundTaskForViewEntity.Status.SUCCESS);
        backgroundTaskForViewRepository.save(background);
        return new VoidOutputValues();
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
    }
}
