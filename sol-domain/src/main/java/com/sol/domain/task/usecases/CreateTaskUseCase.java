package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.HasNoAccessToSpaceException;
import com.sol.domain.space.usecases.FindSpaceByIdUseCase;
import com.sol.domain.task.exceptions.HasNoAccessToTaskException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.task.entity.*;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.domain.task.port.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Создание сущности
 */
public class CreateTaskUseCase extends AbstractCreateUseCase<TaskEntity, TaskIdGenerator<?>, TaskRepository, CreateTaskUseCase.InputValues> {

    private final MeUseCase meUseCase;
    private final FindSpaceByIdUseCase findSpaceByIdUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;

    public CreateTaskUseCase(
            TaskRepository repository,
            TaskIdGenerator idGenerator,
            MeUseCase meUseCase,
            FindSpaceByIdUseCase findSpaceByIdUseCase,
            FindTaskByIdUseCase findTaskByIdUseCase) {
        super(repository, idGenerator);
        this.meUseCase = meUseCase;
        this.findSpaceByIdUseCase = findSpaceByIdUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
    }

    private SolUserEntity solUserEntity(String credentialId){
        return  meUseCase.execute(
                MeUseCase.InputValues
                        .builder()
                        .credentialId(credentialId).build())
                .getEntity();
    }

    private SpaceEntity spaceEntity(String spaceId){
        return findSpaceByIdUseCase.execute(
                IdInputValues.of(spaceId)).getEntity().get();
    }

    private TaskEntity parentTask(String parentTaskId){
        try{
            TaskEntity parentTask = findTaskByIdUseCase.execute(IdInputValues.of(parentTaskId)).getEntity().get();
            return parentTask;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public SingletonEntityOutputValues<TaskEntity> execute(InputValues inputValues) {

        SolUserEntity solUserEntity = solUserEntity(inputValues.getCredentialId());
        SpaceEntity spaceEntity = spaceEntity(inputValues.getSpaceId());
        if(spaceEntity.checkAccess(solUserEntity.getId()) == false){
            throw new HasNoAccessToSpaceException();
        }

        TaskEntity parentTask =  parentTask(inputValues.getParentTaskId());
        if(parentTask != null){
            if(parentTask.checkAccessSpace(spaceEntity.getId()) == false){
                throw new HasNoAccessToTaskException();
            }

            if(parentTask.checkAccess(solUserEntity.getId()) == false){
                throw new HasNoAccessToTaskException();
            }
        }

        TaskEntity taskEntity = new TaskEntity(idGenerator.generate());

        taskEntity.setOwnerId(solUserEntity.getId());
        taskEntity.setParentTaskId(inputValues.parentTaskId);
        taskEntity.setSpaceId(inputValues.spaceId);
        taskEntity.setTitle(inputValues.title);
        taskEntity.setIcon(inputValues.icon);
        taskEntity.setViewIds(inputValues.viewIds);
        taskEntity.setPlanningPoints(inputValues.planningPoints);
        taskEntity.setDeadline(inputValues.deadline);
        taskEntity.setRepeatTaskConfId(inputValues.repeatTaskConfId);
        taskEntity.setCreatedFromRepeatTaskId(null);
        taskEntity.setPics(new ArrayList<>());
        taskEntity.setFiles(new ArrayList<>());
        taskEntity.setDescription("");
        taskEntity.setExternalIds(new ArrayList<>());
        taskEntity.setPointWeight(0);
        taskEntity.setStatus(TaskStatus.OPEN);

        taskEntity = repository.save(taskEntity);

        return SingletonEntityOutputValues.of(taskEntity);
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
        /**
        * viewIds 
        */
        protected List<String> viewIds;
        /**
        * planningPoints 
        */
        protected List<String> planningPoints;
        /**
        * deadline 
        */
        protected LocalDateTime deadline;
        /**
        * repeatTaskConfId 
        */
        protected String repeatTaskConfId;


    }
}