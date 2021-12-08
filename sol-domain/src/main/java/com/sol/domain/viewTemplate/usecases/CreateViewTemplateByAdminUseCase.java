package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.viewTemplate.entity.*;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

/**
 * Создание сущности
 */
public class CreateViewTemplateByAdminUseCase extends AbstractCreateUseCase<ViewTemplateEntity, ViewTemplateIdGenerator<?>, ViewTemplateRepository, CreateViewTemplateByAdminUseCase.InputValues> {


    public CreateViewTemplateByAdminUseCase(ViewTemplateRepository repository, ViewTemplateIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {

        ViewTemplateEntity viewTemplateEntity = new ViewTemplateEntity(idGenerator.generate());

        viewTemplateEntity.setTitle(inputValues.title);
        viewTemplateEntity.setDescription(inputValues.description);
        viewTemplateEntity.setStatus(ViewTemplateEntity.Status.DRAFT);
        viewTemplateEntity.setOwnerType(ViewTemplateEntity.OwnerType.BY_ADMIN);
        viewTemplateEntity.setLanguage(ViewTemplateEntity.Language.ENGLISH);
        viewTemplateEntity.setView(new View());
        viewTemplateEntity.setAddByDefault(false);
        viewTemplateEntity.setCanEdit(false);

        viewTemplateEntity = repository.save(viewTemplateEntity);

        return SingletonEntityOutputValues.of(viewTemplateEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Заголовок 
        */
        protected String title;
        /**
        * description 
        */
        protected String description;
    }
}
