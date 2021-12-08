package com.sol.domain.viewUser.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.view.entity.View;
import com.sol.domain.viewUser.entity.ViewUserEntity;
import com.sol.domain.viewUser.exceptions.ViewUserNotFoundException;
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
public class FindViewBySolUserUseCase extends UseCase<FindViewBySolUserUseCase.InputValues, SingletonEntityOutputValues<List<ViewUserEntity>>> {

    private final ViewUserRepository viewUserRepository;
    private final ViewsSortRepository viewsSortRepository;

    @Override
    public SingletonEntityOutputValues<List<ViewUserEntity>> execute(InputValues inputValues) {
        List<ViewsSortEntity> viewsSortEntities = viewsSortRepository.findBySolUser(inputValues.solUserId);


        ViewsSortEntity root = null;
        for(ViewsSortEntity view: viewsSortEntities){
            if(view.getType().equals(ViewsSortEntity.Type.ROOT)){
                root = view;
                break;
            }
        }

        if(root == null){
            return SingletonEntityOutputValues.of(viewUserRepository.find(inputValues.solUserId));
        }

        List<ViewUserEntity> response = new ArrayList<>();
        for(String viewId: root.getViewsId()){
            Optional<ViewUserEntity> viewUserEntityOptional = viewUserRepository.findById(viewId);
            if(viewUserEntityOptional.isPresent()){
                response.add(viewUserEntityOptional.get());
            }else{
                //TODO: а может удалять из массива
            }
        }

        return SingletonEntityOutputValues.of(response);
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
