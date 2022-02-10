package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class FindAllViewBySolUserUseCase extends UseCase<FindAllViewBySolUserUseCase.InputValues, SingletonEntityOutputValues<List<ViewUserEntity>>> {

    private final ViewUserRepository viewUserRepository;

    @Override
    public SingletonEntityOutputValues<List<ViewUserEntity>> execute(InputValues inputValues) {
        List<ViewUserEntity> viewUserEntities = viewUserRepository.find(inputValues.solUserId);

        return SingletonEntityOutputValues.of(viewUserEntities);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String solUserId;
    }
}
