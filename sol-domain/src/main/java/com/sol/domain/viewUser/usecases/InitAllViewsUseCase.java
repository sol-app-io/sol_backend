package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateDefaultsFilters;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.*;

/**
 * Создание сущности
 */
@RequiredArgsConstructor
public class InitAllViewsUseCase extends UseCase<InitAllViewsUseCase.InputValues, VoidOutputValues> {

    private final ViewTemplateRepository viewTemplateRepository;
    private final CreateViewUserFromTemplateByUserUseCase createViewUserFromTemplateByUserUseCase;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        ViewTemplateDefaultsFilters filters = ViewTemplateDefaultsFilters
                .builder()
                .addByDefault(true)
                .ownerType(ViewTemplateEntity.OwnerType.BY_ADMIN)
                .limit(50l)
                .build();

        SearchResult<ViewTemplateEntity> templates = viewTemplateRepository.find(filters);

        for(ViewTemplateEntity template: templates.getItems()){
           createViewUserFromTemplateByUserUseCase.execute(
                   CreateViewUserFromTemplateByUserUseCase.InputValues.of(template, inputValues.solUserEntity)
           );
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
        protected SolUserEntity solUserEntity;
    }
}
