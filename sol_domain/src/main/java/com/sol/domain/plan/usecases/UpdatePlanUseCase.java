package com.sol.domain.plan.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.plan.entity.PlanEntity;
import com.sol.domain.plan.exceptions.PlanNotFoundException;
import com.sol.domain.plan.port.PlanRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdatePlanUseCase extends UseCase<UpdatePlanUseCase.InputValues, SingletonEntityOutputValues<PlanEntity>> {

    private final PlanRepository planRepository;

    @Override
    public SingletonEntityOutputValues<PlanEntity> execute(InputValues inputValues) {

        PlanEntity planEntity = planRepository.findById(inputValues.getId())
                .orElseThrow(PlanNotFoundException::new);

        // Тут изменение данных
        planEntity.setTitle(inputValues.title);
        planEntity.setDescription(inputValues.description);
        planEntity.setCurrency(inputValues.currency);
        planEntity.setPrice(inputValues.price);
        planEntity.setStatus(inputValues.status);
        planEntity.setPeriod(inputValues.period);
        
        planEntity = planRepository.save(planEntity);

        return SingletonEntityOutputValues.of(planEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String title;
        protected String description;
        protected String currency;
        protected Double price;
        protected PlanStatus status;
        protected Integer period;
    }

}
