package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import com.sol.domain.viewUser.usecases.RemoveAllViewByTemplateForAllUserUseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Удаление сущности
 */
@AllArgsConstructor
public class DeleteViewTemplateUseCase extends UseCase<DeleteViewTemplateUseCase.InputValues, SingletonEntityOutputValues<Boolean>> {

    private final ViewTemplateRepository viewTemplateRepository;
    private final RemoveAllViewByTemplateForAllUserUseCase removeAllViewByTemplateForAllUserUseCase;

    @Override
    public SingletonEntityOutputValues<Boolean> execute(DeleteViewTemplateUseCase.InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate()).orElseThrow(ViewTemplateNotFoundException::new);
        removeAllViewByTemplateForAllUserUseCase.execute(RemoveAllViewByTemplateForAllUserUseCase.InputValues.of(viewTemplateEntity.getId()));
        viewTemplateRepository.delete(viewTemplateEntity.getId());
        return SingletonEntityOutputValues.of(true);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String idTemplate;
    }
}
