package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import lombok.*;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewUserUseCase extends UseCase<UpdateViewUserUseCase.InputValues, SingletonEntityOutputValues<ViewUserEntity>> {

    private final ViewUserRepository viewUserRepository;

    @Override
    public SingletonEntityOutputValues<ViewUserEntity> execute(InputValues inputValues) {
        ViewUserEntity viewUserEntity = viewUserRepository.findById(inputValues.getId())
                .orElseThrow(ViewUserNotFoundException::new);

        viewUserEntity.setOwnerId(inputValues.ownerId);
        viewUserEntity.setCreatedFromTemplateId(inputValues.createdFromTemplateId);
        viewUserEntity.setHasNewTaskToAdd(inputValues.hasNewTaskToAdd);
        viewUserEntity.setHasTaskAdded(inputValues.hasTaskAdded);
        viewUserEntity.setView(inputValues.view);
        
        viewUserEntity = viewUserRepository.save(viewUserEntity);

        return SingletonEntityOutputValues.of(viewUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
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
