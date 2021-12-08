package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

/**
 * Удаление сущности
 */
public class DeleteViewTemplateUseCase extends AbstractDeleteUseCase<String, ViewTemplateRepository> {

    public DeleteViewTemplateUseCase(ViewTemplateRepository repository) {
        super(repository);
    }
}
