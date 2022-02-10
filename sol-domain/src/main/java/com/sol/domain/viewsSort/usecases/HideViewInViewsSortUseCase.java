package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.port.filters.ViewsSortBySolTypeFilters;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class HideViewInViewsSortUseCase extends UseCase<HideViewInViewsSortUseCase.InputValues, SingletonEntityOutputValues<ViewsSortEntity>> {

    private final ViewsSortRepository viewsSortRepository;
    private final FindViewsSortByUserIdUseCase findViewsSortByUserIdUseCase;

    @Override
    public SingletonEntityOutputValues<ViewsSortEntity> execute(InputValues inputValues) {
        ViewsSortEntity viewsSortEntity = findViewsSortByUserIdUseCase.execute(
                FindViewsSortByUserIdUseCase.InputValues.of(inputValues.solUserId, ViewsSortEntity.Type.ROOT))
                .getEntity();

        viewsSortEntity.getViewsId().remove(inputValues.viewId);
        viewsSortEntity = viewsSortRepository.save(viewsSortEntity);
        return SingletonEntityOutputValues.of(viewsSortEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        protected String solUserId;
        protected String viewId;
    }
}
