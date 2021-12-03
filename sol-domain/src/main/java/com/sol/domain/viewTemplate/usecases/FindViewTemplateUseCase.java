package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewTemplate.port.filters.ViewTemplateFilters;

/**
 * Поиск по коллекции
 */
public class FindViewTemplateUseCase extends AbstractFindWithFiltersUseCase<ViewTemplateEntity, ViewTemplateFilters, ViewTemplateRepository> {

    public FindViewTemplateUseCase(ViewTemplateRepository repository) {
        super(repository);
    }
}