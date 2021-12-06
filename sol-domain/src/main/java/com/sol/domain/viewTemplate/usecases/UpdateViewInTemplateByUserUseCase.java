package com.sol.domain.viewTemplate.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.base.entity.Icon;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewTemplate.entity.ViewTemplateEntity;
import com.sol.domain.viewTemplate.exceptions.HasNoAccessToTemplateException;
import com.sol.domain.viewTemplate.exceptions.ViewTemplateNotFoundException;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewInTemplateByUserUseCase extends UseCase<UpdateViewInTemplateByUserUseCase.InputValues, SingletonEntityOutputValues<ViewTemplateEntity>> {

    private final ViewTemplateRepository viewTemplateRepository;

    @Override
    public SingletonEntityOutputValues<ViewTemplateEntity> execute(InputValues inputValues) {
        ViewTemplateEntity viewTemplateEntity = viewTemplateRepository
                .findById(inputValues.getIdTemplate())
                .orElseThrow(ViewTemplateNotFoundException::new);

        if (viewTemplateEntity.getOwnerId().equals(inputValues.solUserId) == false) {
            throw new HasNoAccessToTemplateException(viewTemplateEntity.getId());
        }

        viewTemplateEntity.getView().getIcon().setData(inputValues.getIconEmoji());
        viewTemplateEntity.getView().getIcon().setType(Icon.Type.EMOJI);
        viewTemplateEntity.getView().setTitle(inputValues.getTitle());
        viewTemplateEntity.getView().setDescription(inputValues.getDescription());
        viewTemplateEntity.getView().setAddedType(inputValues.getAddedType());
        viewTemplateEntity.getView().setDisplayMode(inputValues.getDisplayMode());
        viewTemplateEntity.getView().setSortType(inputValues.getSortType());
        viewTemplateEntity.setCanEdit(true);

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
        protected String solUserId;
        protected String iconEmoji;
        protected String title;
        protected String description;
        protected View.AddedType addedType;
        protected View.DisplayMode displayMode;
        protected View.Sort sortType;

    }
}
