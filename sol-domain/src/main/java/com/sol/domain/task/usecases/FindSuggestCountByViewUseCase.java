package com.sol.domain.task.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.HasNoAccessToViewUserException;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindSuggestCountByViewUseCase extends UseCase<FindSuggestCountByViewUseCase.InputValues, SingletonEntityOutputValues<Long>> {

    private final ViewUserRepository viewUserRepository;
    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<Long> execute(InputValues inputValues) {
        ViewUserEntity viewUserEntity = viewUserRepository.findById(inputValues.userViewId).orElseThrow(ViewUserNotFoundException::new);
        if (viewUserEntity.getOwnerId().equals(inputValues.getSolUserId()) == false) {
            throw new HasNoAccessToViewUserException();
        }
        Long count = taskRepository.countSuggest(
                inputValues.solUserId,
                inputValues.userViewId,
                viewUserEntity.getView());

        return SingletonEntityOutputValues.of(count);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String solUserId;
        @NotBlank
        protected String userViewId;
    }
}
