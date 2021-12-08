package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

/**
 * Поиск сущности
 */
public class FindViewTemplateByIdUseCase extends AbstractFindByIdUseCase<String, ViewTemplateEntity, ViewTemplateRepository> {

    public FindViewTemplateByIdUseCase(ViewTemplateRepository repository) {
        super(repository);
    }
}