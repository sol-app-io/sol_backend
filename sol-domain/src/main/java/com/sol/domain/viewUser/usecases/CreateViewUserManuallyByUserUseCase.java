package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundViewForViewUseCase;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewsSort.config.ViewsSortConfig;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.usecases.FindViewsSortByUserIdUseCase;
import com.sol.domain.viewsSort.usecases.UpdateViewsSortUseCase;
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

    private final UpdateViewsSortUseCase updateViewsSortUseCase;
    private final FindViewsSortByUserIdUseCase findViewsSortByUserIdUseCase;
    private final CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase;

    public CreateViewUserManuallyByUserUseCase(
            ViewUserRepository repository,
            ViewUserIdGenerator idGenerator,
            UpdateViewsSortUseCase updateViewsSortUseCase,
            FindViewsSortByUserIdUseCase findViewsSortByUserIdUseCase,
            CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase) {
        super(repository, idGenerator);
        this.updateViewsSortUseCase = updateViewsSortUseCase;
        this.findViewsSortByUserIdUseCase = findViewsSortByUserIdUseCase;
        this.createBackgroundViewForViewUseCase = createBackgroundViewForViewUseCase;
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

        ViewsSortEntity viewsSortEntity = findViewsSortByUserIdUseCase.execute(
                FindViewsSortByUserIdUseCase.InputValues.of(
                        inputValues.ownerId,
                        ViewsSortEntity.Type.ROOT)).getEntity();

        if(viewsSortEntity.getViewsId().contains(viewUserEntity.getId()) == false){
            viewsSortEntity.getViewsId().add(viewUserEntity.getId());
            updateViewsSortUseCase.execute(UpdateViewsSortUseCase.InputValues.of(
                    inputValues.ownerId,
                    ViewsSortEntity.Type.ROOT,
                    viewsSortEntity.getViewsId()));
        }
        createBackgroundViewForViewUseCase.execute(CreateBackgroundViewForViewUseCase.InputValues.of(viewUserEntity.getId()));
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
