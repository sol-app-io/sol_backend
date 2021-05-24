package com.sol.domain.repeatTaskConf.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.repeatTaskConf.entity.*;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfIdGenerator;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;

import java.util.List;

/**
 * Создание сущности
 */
public class CreateRepeatTaskConfUseCase extends AbstractCreateUseCase<RepeatTaskConfEntity, RepeatTaskConfIdGenerator, RepeatTaskConfRepository, CreateRepeatTaskConfUseCase.InputValues> {


    public CreateRepeatTaskConfUseCase(RepeatTaskConfRepository repository, RepeatTaskConfIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<RepeatTaskConfEntity> execute(InputValues inputValues) {

        validate(inputValues);

        RepeatTaskConfEntity repeatTaskConfEntity = new RepeatTaskConfEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        repeatTaskConfEntity.setOwnerId(inputValues.ownerId);
        repeatTaskConfEntity.setCreatedFromTaskId(inputValues.createdFromTaskId);
        repeatTaskConfEntity.setSpaceId(inputValues.spaceId);
        repeatTaskConfEntity.setViewIds(inputValues.viewIds);
        repeatTaskConfEntity.setLastTaskCreatedFromConfId(inputValues.lastTaskCreatedFromConfId);
        repeatTaskConfEntity.setRepeatType(inputValues.repeatType);
        repeatTaskConfEntity.setRepeatValue(inputValues.repeatValue);
        repeatTaskConfEntity.setTasksCreatedFromConf(inputValues.tasksCreatedFromConf);

        repeatTaskConfEntity = repository.save(repeatTaskConfEntity);

        return SingletonEntityOutputValues.of(repeatTaskConfEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected String createdFromTaskId;
        protected String spaceId;
        protected List<String> viewIds;
        protected String lastTaskCreatedFromConfId;
        protected RepeatType repeatType;
        protected Long repeatValue;
        protected List<String> tasksCreatedFromConf;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
