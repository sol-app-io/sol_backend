package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.HasNoAccessToViewUserException;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * Удаление сущности
 */
@RequiredArgsConstructor
public class DeleteViewUserUseCase extends UseCase<DeleteViewUserUseCase.InputValues, VoidOutputValues> {

    private final ViewUserRepository viewUserRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {

        ViewUserEntity viewUserEntity = viewUserRepository
                .findById(inputValues.id)
                .orElseThrow(ViewUserNotFoundException::new);

        if(viewUserEntity.getCanEdit() == false) return new VoidOutputValues();

        if(viewUserEntity.getOwnerId().equals(inputValues.solUserId) == false) throw new HasNoAccessToViewUserException();

        viewUserRepository.delete(inputValues.id);
        //TODO удалить все связи с view

        return new VoidOutputValues();
    }

    private void update(ViewUserEntity viewUserEntity, ViewTemplateEntity template){
        // TODO обновить фоновые задания в UpdateViewsByTemplateUseCase
        viewUserEntity.setView(template.getView());
        viewUserEntity = viewUserRepository.save(viewUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        protected String solUserId;
    }
}
