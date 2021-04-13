package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.entity.*;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedIdGenerator;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedRepository;

/**
 * Создание сущности
 */
public class CreatePushFeedUseCase extends AbstractCreateUseCase<PushFeedEntity, PushFeedIdGenerator, PushFeedRepository, CreatePushFeedUseCase.InputValues> {


    public CreatePushFeedUseCase(PushFeedRepository repository, PushFeedIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<PushFeedEntity> execute(InputValues inputValues) {

        validate(inputValues);

        PushFeedEntity pushFeedEntity = new PushFeedEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        pushFeedEntity.setTitle(inputValues.title);
        pushFeedEntity.setMessage(inputValues.message);
        pushFeedEntity.setParentId(inputValues.parentId);
        pushFeedEntity.setUserId(inputValues.userId);
        pushFeedEntity.setSendingStatus(inputValues.sendingStatus);
        pushFeedEntity.setReadStatus(inputValues.readStatus);
        pushFeedEntity.setPriority(inputValues.priority);
        pushFeedEntity.setError(inputValues.error);

        pushFeedEntity = repository.save(pushFeedEntity);

        return SingletonEntityOutputValues.of(pushFeedEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String title;
        protected String message;
        protected String parentId;
        protected String userId;
        protected SendingStatus sendingStatus;
        protected ReadStatus readStatus;
        protected Priority priority;
        protected String error;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
