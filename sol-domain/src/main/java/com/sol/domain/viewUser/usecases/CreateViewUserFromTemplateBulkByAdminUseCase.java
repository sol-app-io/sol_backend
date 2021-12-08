package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
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
public class CreateViewUserFromTemplateBulkByAdminUseCase extends UseCase<CreateViewUserFromTemplateBulkByAdminUseCase.InputValues, VoidOutputValues> {

    private final SolUserRepository solUserRepository;
    private final ViewUserRepository viewUserRepository;
    private final ViewUserIdGenerator viewUserIdGenerator;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        if (
                inputValues.viewTemplateEntity.getAddByDefault() == false ||
                        inputValues.viewTemplateEntity.getStatus().equals(ViewTemplateEntity.Status.ACTIVE) == false ||
                        inputValues.viewTemplateEntity.getOwnerType().equals(ViewTemplateEntity.OwnerType.BY_ADMIN) == false
        ) {
            return new VoidOutputValues();
        }

        long skip = 0;
        long limit = 1000;

        while (true) {
            SolUserFilters filters = SolUserFilters.builder()
                    .limit(limit)
                    .offset(skip)
                    .build();
            List<SolUserEntity> solUserEntities = solUserRepository.find(filters).getItems();
            skip += limit;

            if (solUserEntities.size() == 0) break;

            for (SolUserEntity solUserEntity : solUserEntities) {
                Optional<ViewUserEntity> viewOptional = viewUserRepository
                        .findByTemplateAndUser(
                                solUserEntity.getId(),
                                inputValues.getViewTemplateEntity().getId());
                if (viewOptional.isPresent()) {
                    ViewUserEntity viewUserEntity = viewOptional.get();
                    viewUserEntity.setView(inputValues.getViewTemplateEntity().getView());
                    viewUserEntity.setCanEdit(inputValues.getViewTemplateEntity().getCanEdit());
                    viewUserRepository.save(viewUserEntity);
                } else {
                    ViewUserEntity viewUserEntity = new ViewUserEntity();
                    viewUserEntity.setId(viewUserIdGenerator.generate());
                    viewUserEntity.setOwnerId(solUserEntity.getId());
                    viewUserEntity.setCreatedFromTemplateId(inputValues.viewTemplateEntity.getId());
                    viewUserEntity.setHasNewTaskToAdd(true);
                    viewUserEntity.setHasTaskAdded(true);
                    viewUserEntity.setCanEdit(inputValues.viewTemplateEntity.getCanEdit());
                    viewUserEntity.setView(inputValues.viewTemplateEntity.getView());
                    viewUserRepository.save(viewUserEntity);
                }
            }
        }

        //TODO поиск taskов
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
    }
}
