package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.VoidOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundViewForViewUseCase;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewUser.port.filters.ViewUserByTemplateFilters;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewsByTemplateUseCase extends UseCase<UpdateViewsByTemplateUseCase.InputValues, VoidOutputValues> {

    private final ViewUserRepository viewUserRepository;
    private final ViewTemplateRepository viewTemplateRepository;
    private final TaskInViewRepository taskInViewRepository;
    private final CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase;

    @Override
    public VoidOutputValues execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate())
                .orElseThrow(ViewTemplateNotFoundException::new);

        long skip = 0;
        long limit = 1000;

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
                update(view, viewTemplateEntity);
            }
        }

        return new VoidOutputValues();
    }

    private void update(ViewUserEntity viewUserEntity, ViewTemplateEntity template){
        viewUserEntity.setView(template.getView());
        viewUserEntity = viewUserRepository.save(viewUserEntity);
        taskInViewRepository.removeByViewId(viewUserEntity.getId());
        createBackgroundViewForViewUseCase.execute(CreateBackgroundViewForViewUseCase.InputValues.of(viewUserEntity.getId()));
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
