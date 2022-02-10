package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
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
    private final TaskInViewRepository taskInViewRepository;

    @Override
    public SingletonEntityOutputValues<ViewUserEntity> execute(InputValues inputValues) {
        ViewUserEntity viewUserEntity = viewUserRepository
                .findById(inputValues.getId())
                .orElseThrow(ViewUserNotFoundException::new);


        if (viewUserEntity.getCanEdit() == false) return SingletonEntityOutputValues.of(viewUserEntity);

        //viewUserEntity.setView(inputValues.view);

        viewUserEntity.getView().setIcon(inputValues.getView().getIcon());
        viewUserEntity.getView().setTitle(inputValues.getView().getTitle());
        viewUserEntity.getView().setDescription(inputValues.getView().getDescription());
        viewUserEntity.getView().setAddedType(inputValues.getView().getAddedType());
        viewUserEntity.getView().setDisplayMode(inputValues.getView().getDisplayMode());
        viewUserEntity.getView().setSortType(inputValues.getView().getSortType());
        viewUserEntity.getView().setViewType(inputValues.getView().getViewType());

        if (inputValues.view.getParams() != null) {
            viewUserEntity.getView().setParams(inputValues.view.getParams());
        }

        viewUserEntity = viewUserRepository.save(viewUserEntity);
        taskInViewRepository.removeByViewId(viewUserEntity.getId());

        return SingletonEntityOutputValues.of(viewUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        protected String solUserId;

        protected View view;

    }
}
