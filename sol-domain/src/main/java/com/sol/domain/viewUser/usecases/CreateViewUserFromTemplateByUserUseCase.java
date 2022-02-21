package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundViewForViewUseCase;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.solUser.port.filters.SolUserFilters;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.*;

import java.util.List;
import java.util.Optional;

/**
 * Создание сущности
 */
@RequiredArgsConstructor
public class CreateViewUserFromTemplateByUserUseCase extends UseCase<CreateViewUserFromTemplateByUserUseCase.InputValues, VoidOutputValues> {

    private final SolUserRepository solUserRepository;
    private final ViewUserRepository viewUserRepository;
    private final ViewUserIdGenerator viewUserIdGenerator;
    private final CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        Optional<ViewUserEntity> viewOptional = viewUserRepository
                .findByTemplateAndUser(
                        inputValues.getSolUserEntity().getId(),
                        inputValues.getViewTemplateEntity().getId());
        if (viewOptional.isPresent()) {
            ViewUserEntity viewUserEntity = viewOptional.get();
            viewUserEntity.setView(inputValues.getViewTemplateEntity().getView());
            viewUserEntity.setHasNewTaskToAdd(true);
            viewUserEntity.setHasTaskAdded(true);
            viewUserRepository.save(viewUserEntity);
            createBackgroundViewForViewUseCase.execute(CreateBackgroundViewForViewUseCase.InputValues.of(viewUserEntity.getId()));
        } else {
            ViewUserEntity viewUserEntity = new ViewUserEntity();
            viewUserEntity.setId(viewUserIdGenerator.generate());
            viewUserEntity.setOwnerId(inputValues.solUserEntity.getId());
            viewUserEntity.setCreatedFromTemplateId(inputValues.viewTemplateEntity.getId());
            viewUserEntity.setHasNewTaskToAdd(true);
            viewUserEntity.setHasTaskAdded(true);
            viewUserEntity.setCanEdit(inputValues.viewTemplateEntity.getCanEdit());
            viewUserEntity.setView(inputValues.viewTemplateEntity.getView());
            viewUserRepository.save(viewUserEntity);
            createBackgroundViewForViewUseCase.execute(CreateBackgroundViewForViewUseCase.InputValues.of(viewUserEntity.getId()));
        }

        return new VoidOutputValues();
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
         * Created from template
         */
        protected ViewTemplateEntity viewTemplateEntity;
        protected SolUserEntity solUserEntity;
    }
}
