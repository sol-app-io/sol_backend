package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateAdminsFilters;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;
import lombok.*;

/**
 * Поиск по коллекции
 */
@RequiredArgsConstructor
public class FindViewTemplateForAdminUseCase extends UseCase<FindViewTemplateForAdminUseCase.InputValues, SearchResultEntityOutputValues<ViewTemplateEntity>> {

    private final ViewTemplateRepository viewTemplateRepository;

    @Override
    public SearchResultEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {
        return SearchResultEntityOutputValues.of(viewTemplateRepository.find(inputValues.getFilters()));
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        protected ViewTemplateAdminsFilters filters;
    }
}
