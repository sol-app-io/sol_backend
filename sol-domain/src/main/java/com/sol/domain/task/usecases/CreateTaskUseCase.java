package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundTaskForViewUseCase;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.base.utils.DateUtils;
import com.sol.domain.space.usecases.UpdateTaskCountInSpaceUseCase;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.usecases.FindInboxSpaceByOwnerIdUseCase;
import com.sol.domain.space.usecases.FindSpaceByIdUseCase;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.task.entity.*;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.domain.task.port.TaskRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

/**
 * Создание сущности
 */
public class CreateTaskUseCase extends AbstractCreateUseCase<TaskEntity, TaskIdGenerator<?>, TaskRepository, CreateTaskUseCase.InputValues> {

    private final MeUseCase meUseCase;
    private final FindSpaceByIdUseCase findSpaceByIdUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final FindInboxSpaceByOwnerIdUseCase findInboxSpaceByOwnerIdUseCase;
    private final UpdateTaskCountInSpaceUseCase updateTaskCountInSpaceUseCase;
    private final CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase;

    public CreateTaskUseCase(
            TaskRepository repository,
            TaskIdGenerator idGenerator,
            MeUseCase meUseCase,
            FindSpaceByIdUseCase findSpaceByIdUseCase,
            FindTaskByIdUseCase findTaskByIdUseCase,
            FindInboxSpaceByOwnerIdUseCase findInboxSpaceByOwnerIdUseCase,
            UpdateTaskCountInSpaceUseCase updateTaskCountInSpaceUseCase,
            CreateBackgroundTaskForViewUseCase createBackgroundTaskForViewUseCase) {
        super(repository, idGenerator);
        this.meUseCase = meUseCase;
        this.findSpaceByIdUseCase = findSpaceByIdUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
        this.findInboxSpaceByOwnerIdUseCase = findInboxSpaceByOwnerIdUseCase;
        this.updateTaskCountInSpaceUseCase = updateTaskCountInSpaceUseCase;
        this.createBackgroundTaskForViewUseCase = createBackgroundTaskForViewUseCase;
    }

    private SolUserEntity solUserEntity(String credentialId) {
        return meUseCase.execute(
                MeUseCase.InputValues
                        .builder()
                        .credentialId(credentialId).build())
                .getEntity();
    }

    private Optional<SpaceEntity> spaceEntity(String spaceId, String solUserId) {
        if (spaceId == null) {
            return Optional.ofNullable(
                    findInboxSpaceByOwnerIdUseCase.execute(
                            FindInboxSpaceByOwnerIdUseCase.InputValues.of(solUserId)).getEntity());
        }
        return findSpaceByIdUseCase.execute(IdInputValues.of(spaceId)).getEntity();
    }

    private TaskEntity parentTask(String parentTaskId) {
        try {
            TaskEntity parentTask = findTaskByIdUseCase.execute(IdInputValues.of(parentTaskId)).getEntity().get();
            return parentTask;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {

        SolUserEntity solUserEntity = solUserEntity(inputValues.getCredentialId());
        Optional<SpaceEntity> spaceEntityOptional = spaceEntity(inputValues.getSpaceId(), solUserEntity.getId());
        if (spaceEntityOptional.isEmpty()) throw new SpaceNotFoundException();

        SpaceEntity spaceEntity = spaceEntityOptional.get();

        if (spaceEntity.checkAccess(solUserEntity.getId()) == false) {
            throw new HasNoAccessToSpaceException();
        }

        TaskEntity parentTask = parentTask(inputValues.getParentTaskId());
        if (parentTask != null) {
            if (parentTask.checkAccessSpace(spaceEntity.getId()) == false) {
                throw new HasNoAccessToTaskException();
            }

            if (parentTask.checkAccess(solUserEntity.getId()) == false) {
                throw new HasNoAccessToTaskException();
            }
        }

        TaskEntity taskEntity = new TaskEntity(idGenerator.generate());

        taskEntity.setOwnerId(solUserEntity.getId());
        taskEntity.setParentTaskId(parentTask != null ? parentTask.getId() : null);
        taskEntity.setSpaceId(spaceEntity.getId());
        taskEntity.setTitle(inputValues.title);
        taskEntity.setIcon(inputValues.icon);
        taskEntity.setViewIds(new HashSet<>());
        taskEntity.setDeadlineType(inputValues.deadlineType);
        taskEntity.setDeadline(DateUtils.convert(inputValues.deadline, inputValues.timezone));
        taskEntity.setTimezone(inputValues.timezone);
        taskEntity.setRepeatTaskConfId(null);
        taskEntity.setCreatedFromRepeatTaskId(null);
        taskEntity.setPics(new ArrayList<>());
        taskEntity.setFiles(new ArrayList<>());
        taskEntity.setDescription("");
        taskEntity.setExternalIds(new ArrayList<>());
        taskEntity.setSlotsMilliseconds(0l);
        taskEntity.setStatus(TaskStatus.OPEN);

        if(inputValues.parentTaskId == null){
            taskEntity.setSortNum(repository.countBySpaceId(inputValues.spaceId, TaskStatus.open()).intValue());
        }else{
            taskEntity.setSortNum(repository.countByParentId(inputValues.parentTaskId, TaskStatus.open()).intValue());
        }

        taskEntity = repository.save(taskEntity);

        updateTaskCountInSpaceUseCase.execute(UpdateTaskCountInSpaceUseCase.InputValues.of(spaceEntity.getId()));
        createBackgroundTaskForViewUseCase.execute(CreateBackgroundTaskForViewUseCase.InputValues.of(taskEntity.getId()));

        return SingletonEntityOutputValues.of(repository.findById(taskEntity.getId()).get());
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
         * ownerId
         */
        protected String credentialId;
        /**
         * parentTaskId
         */
        protected String parentTaskId;
        /**
         * spaceId
         */
        protected String spaceId;
        /**
         * Заголовок
         */
        protected String title;
        /**
         * icon
         */
        protected Icon icon;

        protected Long deadline;
        protected DeadlineType deadlineType;
        protected Integer timezone;

    }
}
