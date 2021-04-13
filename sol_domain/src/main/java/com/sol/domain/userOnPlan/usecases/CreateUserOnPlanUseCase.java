package com.sol.domain.userOnPlan.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.userOnPlan.entity.*;
import com.sol.domain.userOnPlan.port.UserOnPlanIdGenerator;
import com.sol.domain.userOnPlan.port.UserOnPlanRepository;

import java.time.LocalDate;

/**
 * Создание сущности
 */
public class CreateUserOnPlanUseCase extends AbstractCreateUseCase<UserOnPlanEntity, UserOnPlanIdGenerator, UserOnPlanRepository, CreateUserOnPlanUseCase.InputValues> {


    public CreateUserOnPlanUseCase(UserOnPlanRepository repository, UserOnPlanIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<UserOnPlanEntity> execute(InputValues inputValues) {

        validate(inputValues);

        UserOnPlanEntity userOnPlanEntityEntity = new UserOnPlanEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        userOnPlanEntityEntity.setUserId(inputValues.userId);
        userOnPlanEntityEntity.setPlanId(inputValues.planId);
        userOnPlanEntityEntity.setStartDate(inputValues.startDate);
        userOnPlanEntityEntity.setEndDate(inputValues.endDate);

        userOnPlanEntityEntity = repository.save(userOnPlanEntityEntity);

        return SingletonEntityOutputValues.of(userOnPlanEntityEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String userId;
        protected String planId;
        protected LocalDate startDate;
        protected LocalDate endDate;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
