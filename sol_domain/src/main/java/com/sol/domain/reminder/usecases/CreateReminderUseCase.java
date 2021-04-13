package com.sol.domain.reminder.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sol.domain.reminder.entity.*;
import com.sol.domain.reminder.port.ReminderIdGenerator;
import com.sol.domain.reminder.port.ReminderRepository;

/**
 * Создание сущности
 */
public class CreateReminderUseCase extends AbstractCreateUseCase<ReminderEntity, ReminderIdGenerator, ReminderRepository, CreateReminderUseCase.InputValues> {


    public CreateReminderUseCase(ReminderRepository repository, ReminderIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<ReminderEntity> execute(InputValues inputValues) {

        validate(inputValues);

        ReminderEntity reminderEntity = new ReminderEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        reminderEntity.setOwnerId(inputValues.ownerId);
        reminderEntity.setTaskId(inputValues.taskId);
        reminderEntity.setSpaceId(inputValues.spaceId);
        reminderEntity.setViewIds(inputValues.viewIds);
        reminderEntity.setReminderTime(inputValues.reminderTime);

        reminderEntity = repository.save(reminderEntity);

        return SingletonEntityOutputValues.of(reminderEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected String taskId;
        protected String spaceId;
        protected List&lt;String&gt; viewIds;
        protected DateTime reminderTime;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
