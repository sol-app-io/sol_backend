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
public class CreateViewUserManuallyByUserUseCase extends AbstractCreateUseCase<ViewUserEntity, ViewUserIdGenerator<?>, ViewUserRepository, CreateViewUserManuallyByUserUseCase.InputValues> {


    public CreateViewUserManuallyByUserUseCase(ViewUserRepository repository, ViewUserIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewUserEntity> execute(InputValues inputValues) {

        ViewUserEntity viewUserEntity = new ViewUserEntity(idGenerator.generate());

        viewUserEntity.setOwnerId(inputValues.ownerId);
        viewUserEntity.setCreatedFromTemplateId(null);
        viewUserEntity.setHasNewTaskToAdd(false);
        viewUserEntity.setHasTaskAdded(false);
        viewUserEntity.setView(inputValues.view);
        viewUserEntity.setCanEdit(true);

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
        * view
        */
        protected View view;

    }
}
