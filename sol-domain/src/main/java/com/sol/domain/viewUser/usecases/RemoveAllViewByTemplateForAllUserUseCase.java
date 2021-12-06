package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class RemoveAllViewByTemplateForAllUserUseCase extends UseCase<RemoveAllViewByTemplateForAllUserUseCase.InputValues, VoidOutputValues> {

    private final ViewUserRepository viewUserRepository;
    private final ViewTemplateRepository viewTemplateRepository;
    private final ViewsSortRepository viewsSortRepository;
    private final TaskInViewRepository taskInViewRepository;
    private final BackgroundTaskForViewRepository backgroundTaskForViewRepository;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate())
                .orElseThrow(ViewTemplateNotFoundException::new);

        Long skip = 0l;
        Long limit = 1000l;

        while (true) {
            ViewUserByTemplateFilters filters = ViewUserByTemplateFilters
                    .builder()
                    .templateId(viewTemplateEntity.getId())
                    .limit(limit)
                    .offset(skip)
                    .build();

            SearchResult<ViewUserEntity> views = viewUserRepository.find(filters);

            if (views.getItems().size() == 0) break;

            for (ViewUserEntity view : views.getItems()) {
                viewUserRepository.delete(view.getId());
                viewsSortRepository.removeByViewId(view.getId());
                taskInViewRepository.removeByViewId(view.getId());
                backgroundTaskForViewRepository.removeByViewId(view.getId());
            }
        }

        return new VoidOutputValues();
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
