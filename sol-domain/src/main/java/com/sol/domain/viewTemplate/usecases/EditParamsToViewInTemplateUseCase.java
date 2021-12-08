package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class EditParamsToViewInTemplateUseCase extends UseCase<EditParamsToViewInTemplateUseCase.InputValues, SingletonEntityOutputValues<ViewTemplateEntity>> {

    private final ViewTemplateRepository viewTemplateRepository;

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate())
                .orElseThrow(ViewTemplateNotFoundException::new);

        for (View.Params params : viewTemplateEntity.getView().getParams()) {
            if (params.getId().equals(inputValues.idParams)) {
                params.setType(inputValues.type);
                params.setValueBool(inputValues.valueBool);
                params.setValueString(inputValues.valueString);
                params.setValueDate(inputValues.valueDate);
            }
        }

        viewTemplateEntity = viewTemplateRepository.save(viewTemplateEntity);

        return SingletonEntityOutputValues.of(viewTemplateEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String idTemplate;
        @NotBlank
        protected String idParams;

        protected View.Params.Type type;
        protected String valueString;
        protected LocalDateTime valueDate;
        protected Boolean valueBool;
    }
}
