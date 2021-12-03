package com.sol.domain.viewsSort.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;
import com.sol.domain.viewsSort.entity.ViewsSortEntity;
import com.sol.domain.viewsSort.exceptions.ViewsSortNotFoundException;
import com.sol.domain.viewsSort.port.ViewsSortRepository;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateViewsSortUseCase extends UseCase<UpdateViewsSortUseCase.InputValues, SingletonEntityOutputValues<ViewsSortEntity>> {

    private final ViewsSortRepository viewsSortRepository;

    @Override
    public SingletonEntityOutputValues<ViewsSortEntity> execute(InputValues inputValues) {
        ViewsSortEntity viewsSortEntity = viewsSortRepository.findById(inputValues.getId())
                .orElseThrow(ViewsSortNotFoundException::new);

        viewsSortEntity.setOwnerId(inputValues.ownerId);
        viewsSortEntity.setType(inputValues.type);
        
        viewsSortEntity = viewsSortRepository.save(viewsSortEntity);

        return SingletonEntityOutputValues.of(viewsSortEntity);
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
        * Type 
        */
        protected String type;
        /**
        * Views 
        */
        protected List<String> viewsId = new ArrayList<>();

    }
}
