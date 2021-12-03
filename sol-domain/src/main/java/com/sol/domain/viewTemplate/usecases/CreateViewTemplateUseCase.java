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
public class CreateViewTemplateUseCase extends AbstractCreateUseCase<ViewTemplateEntity, ViewTemplateIdGenerator<?>, ViewTemplateRepository, CreateViewTemplateUseCase.InputValues> {


    public CreateViewTemplateUseCase(ViewTemplateRepository repository, ViewTemplateIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {

        ViewTemplateEntity viewTemplateEntity = new ViewTemplateEntity(idGenerator.generate());

        viewTemplateEntity.setOwnerId(inputValues.ownerId);
        viewTemplateEntity.setTitle(inputValues.title);
        viewTemplateEntity.setDescription(inputValues.description);
        viewTemplateEntity.setCreatedFromViewId(inputValues.createdFromViewId);
        viewTemplateEntity.setStatus(inputValues.status);
        viewTemplateEntity.setOwnerType(inputValues.ownerType);
        viewTemplateEntity.setLanguage(inputValues.language);
        viewTemplateEntity.setView(inputValues.view);
        viewTemplateEntity.setAddByDefault(inputValues.addByDefault);

        viewTemplateEntity = repository.save(viewTemplateEntity);

        return SingletonEntityOutputValues.of(viewTemplateEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        /**
        * Owner 
        */
        protected String ownerId;
        /**
        * Заголовок 
        */
        protected String title;
        /**
        * description 
        */
        protected String description;
        /**
        * Parent view 
        */
        protected String createdFromViewId;
        /**
        * Status 
        */
        protected ViewTemplateEntity.Status status;
        /**
        * Owner type 
        */
        protected ViewTemplateEntity.OwnerType ownerType;
        /**
        * language 
        */
        protected ViewTemplateEntity.Language language;
        /**
        * view 
        */
        protected View view;
        /**
        * added by default 
        */
        protected Boolean addByDefault;

    }
}
