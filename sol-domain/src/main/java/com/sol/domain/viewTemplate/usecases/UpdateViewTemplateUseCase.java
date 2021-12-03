package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import lombok.*;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewTemplateUseCase extends UseCase<UpdateViewTemplateUseCase.InputValues, SingletonEntityOutputValues<ViewTemplateEntity>> {

    private final ViewTemplateRepository viewTemplateRepository;

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository.findById(inputValues.getId())
                .orElseThrow(ViewTemplateNotFoundException::new);

        viewTemplateEntity.setOwnerId(inputValues.ownerId);
        viewTemplateEntity.setTitle(inputValues.title);
        viewTemplateEntity.setDescription(inputValues.description);
        viewTemplateEntity.setCreatedFromViewId(inputValues.createdFromViewId);
        viewTemplateEntity.setStatus(inputValues.status);
        viewTemplateEntity.setOwnerType(inputValues.ownerType);
        viewTemplateEntity.setLanguage(inputValues.language);
        viewTemplateEntity.setView(inputValues.view);
        viewTemplateEntity.setAddByDefault(inputValues.addByDefault);
        
        viewTemplateEntity = viewTemplateRepository.save(viewTemplateEntity);

        return SingletonEntityOutputValues.of(viewTemplateEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
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
