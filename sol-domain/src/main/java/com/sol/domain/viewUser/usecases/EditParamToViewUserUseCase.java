package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.backgroundTaskForView.usecases.CreateBackgroundViewForViewUseCase;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.HasNoAccessToViewUserException;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class EditParamToViewUserUseCase extends UseCase<EditParamToViewUserUseCase.InputValues, SingletonEntityOutputValues<ViewUserEntity>> {

    private final ViewUserRepository viewUserRepository;
    private final TaskInViewRepository taskInViewRepository;
    private final CreateBackgroundViewForViewUseCase createBackgroundViewForViewUseCase;

    @Override
    public SingletonEntityOutputValues<ViewUserEntity> execute(InputValues inputValues) {
        ViewUserEntity viewUserEntity = viewUserRepository
                .findById(inputValues.getId())
                .orElseThrow(ViewUserNotFoundException::new);


        if (viewUserEntity.getCanEdit() == false) return SingletonEntityOutputValues.of(viewUserEntity);
        if (viewUserEntity.getOwnerId().equals(inputValues.getSolUserId()) == false)
            throw new HasNoAccessToViewUserException();

        for (View.Params params : viewUserEntity.getView().getParams()) {
            if (params.getId().equals(inputValues.getParamId())) {
                params.setType(inputValues.type);
                params.setValueBool(inputValues.valueBool);
                params.setValueString(inputValues.valueString);
                params.setValueDate(inputValues.valueDate);
            }
        }
        viewUserEntity = viewUserRepository.save(viewUserEntity);
        taskInViewRepository.removeByViewId(viewUserEntity.getId());
        createBackgroundViewForViewUseCase.execute(CreateBackgroundViewForViewUseCase.InputValues.of(viewUserEntity.getId()));
        return SingletonEntityOutputValues.of(viewUserEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String id;
        protected String solUserId;
        protected String paramId;
        protected View.Params.Type type;
        protected String valueString;
        protected LocalDateTime valueDate;
        protected Boolean valueBool;

    }
}
