package com.sol.domain.plan.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.plan.entity.*;
import com.sol.domain.plan.port.PlanIdGenerator;
import com.sol.domain.plan.port.PlanRepository;

/**
 * Создание сущности
 */
public class CreatePlanUseCase extends AbstractCreateUseCase<PlanEntity, PlanIdGenerator, PlanRepository, CreatePlanUseCase.InputValues> {


    public CreatePlanUseCase(PlanRepository repository, PlanIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<PlanEntity> execute(InputValues inputValues) {

        validate(inputValues);

        PlanEntity planEntity = new PlanEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        planEntity.setTitle(inputValues.title);
        planEntity.setDescription(inputValues.description);
        planEntity.setCurrency(inputValues.currency);
        planEntity.setPrice(inputValues.price);
        planEntity.setStatus(inputValues.status);
        planEntity.setPeriod(inputValues.period);

        planEntity = repository.save(planEntity);

        return SingletonEntityOutputValues.of(planEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String title;
        protected String description;
        protected String currency;
        protected Double price;
        protected PlanStatus status;
        protected Integer period;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
