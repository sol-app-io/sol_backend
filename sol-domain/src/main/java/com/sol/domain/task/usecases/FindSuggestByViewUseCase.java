package com.sol.domain.task.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;
import com.sol.domain.task.entity.TaskEntity;
import com.sol.domain.task.port.TaskRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.HasNoAccessToViewUserException;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Поиск Suggest подходящих
 */
@RequiredArgsConstructor
public class FindSuggestByViewUseCase extends UseCase<FindSuggestByViewUseCase.InputValues, SearchResultEntityOutputValues<TaskEntity>> {

    private final ViewUserRepository viewUserRepository;
    private final TaskRepository taskRepository;

    @Override
    public SearchResultEntityOutputValues<TaskEntity> execute(InputValues inputValues) {
        ViewUserEntity viewUserEntity = viewUserRepository.findById(inputValues.userViewId).orElseThrow(ViewUserNotFoundException::new);
        if (viewUserEntity.getOwnerId().equals(inputValues.getSolUserId()) == false) {
            throw new HasNoAccessToViewUserException();
        }
        List<TaskEntity> taskEntities = taskRepository.findSuggest(
                inputValues.solUserId,
                inputValues.userViewId,
                viewUserEntity.getView());

        return SearchResultEntityOutputValues.of(SearchResult.withItemsAndCount(taskEntities, Long.valueOf(taskEntities.size())));
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
