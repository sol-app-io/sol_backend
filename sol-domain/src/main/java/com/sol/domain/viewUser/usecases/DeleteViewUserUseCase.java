package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.taskInView.usecases.DeleteAllTaskInViewByViewUseCase;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.HasNoAccessToViewUserException;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.usecases.FindViewsSortByUserIdUseCase;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * Удаление сущности
 */
@RequiredArgsConstructor
public class DeleteViewUserUseCase extends UseCase<DeleteViewUserUseCase.InputValues, VoidOutputValues> {

    private final ViewUserRepository viewUserRepository;
    private final DeleteAllTaskInViewByViewUseCase deleteAllTaskInViewByViewUseCase;
    private final FindViewsSortByUserIdUseCase findViewsSortByUserIdUseCase;
    private final ViewsSortRepository viewsSortRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        System.out.println("");
        System.out.println("=============================================");
        System.out.println("Run - ViewUserEntity viewUserEntity = viewUserRepository");
        ViewUserEntity viewUserEntity = viewUserRepository
                .findById(inputValues.id)
                .orElseThrow(ViewUserNotFoundException::new);

        if(viewUserEntity.getCanEdit() == false) return new VoidOutputValues();
        if(viewUserEntity.getOwnerId().equals(inputValues.solUserId) == false) throw new HasNoAccessToViewUserException();

        System.out.println("Run - ViewsSortEntity viewsSortEntity = findViewsSortByUserIdUseCase.execute(FindViewsSortByUserIdUseCase.InputValues.of(inputValues.getSolUserId(), ViewsSortEntity.Type.ROOT)).getEntity();");
        ViewsSortEntity viewsSortEntity = findViewsSortByUserIdUseCase.execute(FindViewsSortByUserIdUseCase.InputValues.of(inputValues.getSolUserId(), ViewsSortEntity.Type.ROOT)).getEntity();
        System.out.println("Run - viewsSortEntity.getViewsId().remove(inputValues.id);");
        viewsSortEntity.getViewsId().remove(inputValues.id);
        viewsSortRepository.save(viewsSortEntity);

        System.out.println("Run - viewUserRepository.delete(inputValues.id);");
        viewUserRepository.delete(inputValues.id);
        System.out.println("Run - deleteAllTaskInViewByViewUseCase.execute(DeleteAllTaskInViewByViewUseCase.InputValues.of(viewUserEntity.getId()));");
        deleteAllTaskInViewByViewUseCase.execute(DeleteAllTaskInViewByViewUseCase.InputValues.of(viewUserEntity.getId()));
        System.out.println("=============================================");
        return new VoidOutputValues();
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
