package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.entity.*;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfRepository;

/**
 * Создание сущности
 */
public class CreatePushNotificationConfUseCase extends AbstractCreateUseCase<PushNotificationConfEntity, PushNotificationConfIdGenerator, PushNotificationConfRepository, CreatePushNotificationConfUseCase.InputValues> {


    public CreatePushNotificationConfUseCase(PushNotificationConfRepository repository, PushNotificationConfIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<PushNotificationConfEntity> execute(InputValues inputValues) {

        validate(inputValues);

        PushNotificationConfEntity pushNotificationConfEntity = new PushNotificationConfEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        pushNotificationConfEntity.setTitle(inputValues.title);
        pushNotificationConfEntity.setMessage(inputValues.message);
        pushNotificationConfEntity.setDeviceId(inputValues.deviceId);
        pushNotificationConfEntity.setPushFeedId(inputValues.pushFeedId);
        pushNotificationConfEntity.setparentId(inputValues.parentId);
        pushNotificationConfEntity.setUserId(inputValues.userId);
        pushNotificationConfEntity.setDeviceType(inputValues.deviceType);
        pushNotificationConfEntity.setSendingStatus(inputValues.sendingStatus);
        pushNotificationConfEntity.setReadStatus(inputValues.readStatus);

        pushNotificationConfEntity = repository.save(pushNotificationConfEntity);

        return SingletonEntityOutputValues.of(pushNotificationConfEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String title;
        protected String message;
        protected String deviceId;
        protected String pushFeedId;
        protected String parentId;
        protected String userId;
        protected DeviceType deviceType;
        protected SendingStatus sendingStatus;
        protected ReadStatus readStatus;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
