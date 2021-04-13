package com.sol.domain.userOnPlan.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.userOnPlan.entity.UserOnPlanEntity;
import com.sol.domain.userOnPlan.exceptions.UserOnPlanNotFoundException;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateUserOnPlanUseCase extends UseCase<UpdateUserOnPlanUseCase.InputValues, SingletonEntityOutputValues<UserOnPlanEntity>> {

    private final UserOnPlanRepository userOnPlanEntityRepository;

    @Override
    public SingletonEntityOutputValues<UserOnPlanEntity> execute(InputValues inputValues) {

        UserOnPlanEntity userOnPlanEntityEntity = userOnPlanEntityRepository.findById(inputValues.getId())
                .orElseThrow(UserOnPlanNotFoundException::new);

        // Тут изменение данных
        userOnPlanEntityEntity.setUserId(inputValues.userId);
        userOnPlanEntityEntity.setPlanId(inputValues.planId);
        userOnPlanEntityEntity.setStartDate(inputValues.startDate);
        userOnPlanEntityEntity.setEndDate(inputValues.endDate);
        
        userOnPlanEntityEntity = userOnPlanEntityRepository.save(userOnPlanEntityEntity);

        return SingletonEntityOutputValues.of(userOnPlanEntityEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String userId;
        protected String planId;
        protected Date startDate;
        protected Date endDate;
    }

}
