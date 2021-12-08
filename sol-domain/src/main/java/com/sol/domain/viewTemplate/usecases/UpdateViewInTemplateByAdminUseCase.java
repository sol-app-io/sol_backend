package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.view.entity.View;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewInTemplateByAdminUseCase extends UseCase<UpdateViewInTemplateByAdminUseCase.InputValues, SingletonEntityOutputValues<ViewTemplateEntity>> {

    private final ViewTemplateRepository viewTemplateRepository;

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate())
                .orElseThrow(ViewTemplateNotFoundException::new);

        viewTemplateEntity.getView().getIcon().setData(inputValues.getIconEmoji());
        viewTemplateEntity.getView().getIcon().setType(Icon.Type.EMOJI);
        viewTemplateEntity.getView().setTitle(inputValues.getViewTitle());
        viewTemplateEntity.getView().setDescription(inputValues.getViewDescription());
        viewTemplateEntity.getView().setAddedType(inputValues.getAddedType());
        viewTemplateEntity.getView().setDisplayMode(inputValues.getDisplayMode());
        viewTemplateEntity.getView().setSortType(inputValues.getSortType());
        viewTemplateEntity.setCanEdit(inputValues.getCanEdit());
        viewTemplateEntity.setTitle(inputValues.title);
        viewTemplateEntity.setDescription(inputValues.description);
        viewTemplateEntity.setAddByDefault(inputValues.addByDefault);
        viewTemplateEntity.setStatus(inputValues.status != null ? inputValues.status : ViewTemplateEntity.Status.DRAFT);

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

        protected String iconEmoji;
        protected String title;
        protected String description;
        protected String viewTitle;
        protected String viewDescription;
        protected View.AddedType addedType;
        protected View.DisplayMode displayMode;
        protected View.Sort sortType;
        protected Boolean canEdit;
        protected Boolean addByDefault;
        protected ViewTemplateEntity.Status status;

    }
}
