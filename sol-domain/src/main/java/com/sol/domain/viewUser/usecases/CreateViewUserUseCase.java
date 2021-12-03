package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.viewUser.entity.*;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;

/**
 * Создание сущности
 */
public class CreateViewUserUseCase extends AbstractCreateUseCase<ViewUserEntity, ViewUserIdGenerator<?>, ViewUserRepository, CreateViewUserUseCase.InputValues> {


    public CreateViewUserUseCase(ViewUserRepository repository, ViewUserIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewUserEntity> execute(InputValues inputValues) {

        ViewUserEntity viewUserEntity = new ViewUserEntity(idGenerator.generate());

        viewUserEntity.setOwnerId(inputValues.ownerId);
        viewUserEntity.setCreatedFromTemplateId(inputValues.createdFromTemplateId);
        viewUserEntity.setHasNewTaskToAdd(inputValues.hasNewTaskToAdd);
        viewUserEntity.setHasTaskAdded(inputValues.hasTaskAdded);
        viewUserEntity.setView(inputValues.view);

        viewUserEntity = repository.save(viewUserEntity);

        return SingletonEntityOutputValues.of(viewUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Owner 
        */
        protected String ownerId;
        /**
        * Created from template 
        */
        protected String createdFromTemplateId;
        /**
        * Has new task 
        */
        protected Boolean hasNewTaskToAdd;
        /**
        * Has new task 
        */
        protected Boolean hasTaskAdded;
        /**
        * view 
        */
        protected View view;

    }
}
