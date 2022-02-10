package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.exceptions.ViewsSortNotFoundException;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import com.sol.domain.viewsSort.port.filters.ViewsSortBySolTypeFilters;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Поиск сущности
 */
@RequiredArgsConstructor
public class FindViewsSortByUserIdUseCase extends UseCase<FindViewsSortByUserIdUseCase.InputValues, SingletonEntityOutputValues<ViewsSortEntity>> {

    private final ViewsSortRepository viewsSortRepository;
    private final ViewUserRepository viewUserRepository;
    private final ViewsSortIdGenerator viewsSortIdGenerator;

    @Override
    public SingletonEntityOutputValues<ViewsSortEntity> execute(InputValues inputValues) {
        System.out.println("Run - ViewsSortBySolTypeFilters filters = ViewsSortBySolTypeFilters");
        ViewsSortBySolTypeFilters filters = ViewsSortBySolTypeFilters
                .builder()
                .solUserId(inputValues.solUserId)
                .type(inputValues.type)
                .build();

        System.out.println("Run - Optional<ViewsSortEntity> viewsSortEntityOptional = viewsSortRepository.find(filters);");
        Optional<ViewsSortEntity> viewsSortEntityOptional = viewsSortRepository.find(filters);
        ViewsSortEntity viewsSortEntity;
        if(viewsSortEntityOptional.isPresent()){
            viewsSortEntity = viewsSortEntityOptional.get();
        }else{
            viewsSortEntity = new ViewsSortEntity();
            viewsSortEntity.setId(viewsSortIdGenerator.generate());
            System.out.println("Run - viewsSortEntity.setViewsId(viewUserRepository");
            viewsSortEntity.setViewsId(viewUserRepository
                    .find(inputValues.solUserId)
                    .stream()
                    .map(item -> item.getId())
                    .collect(Collectors.toList()));
            viewsSortEntity.setOwnerId(inputValues.solUserId);
            viewsSortEntity.setType(inputValues.type);
            System.out.println("Run - viewsSortRepository.save(viewsSortEntity);");
            viewsSortRepository.save(viewsSortEntity);
        }

        return SingletonEntityOutputValues.of(viewsSortEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor(staticName = "empty")
    @Getter
    @Builder
    public static class InputValues implements UseCase.InputValues {
        @NotBlank
        protected String solUserId;
        protected ViewsSortEntity.Type type;
    }
}
