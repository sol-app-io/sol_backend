package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.entity.*;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceRepository;

/**
 * Создание сущности
 */
public class CreatePushToDeviceUseCase extends AbstractCreateUseCase<PushToDeviceEntity, PushToDeviceIdGenerator, PushToDeviceRepository, CreatePushToDeviceUseCase.InputValues> {


    public CreatePushToDeviceUseCase(PushToDeviceRepository repository, PushToDeviceIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<PushToDeviceEntity> execute(InputValues inputValues) {

        validate(inputValues);

        PushToDeviceEntity pushToDeviceEntity = new PushToDeviceEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        pushToDeviceEntity.setTitle(inputValues.title);
        pushToDeviceEntity.setMessage(inputValues.message);
        pushToDeviceEntity.setDeviceId(inputValues.deviceId);
        pushToDeviceEntity.setPushFeedId(inputValues.pushFeedId);
        pushToDeviceEntity.setParentId(inputValues.parentId);
        pushToDeviceEntity.setUserId(inputValues.userId);
        pushToDeviceEntity.setDeviceType(inputValues.deviceType);
        pushToDeviceEntity.setSendingStatus(inputValues.sendingStatus);
        pushToDeviceEntity.setDeadStatus(inputValues.readStatus);

        pushToDeviceEntity = repository.save(pushToDeviceEntity);

        return SingletonEntityOutputValues.of(pushToDeviceEntity);
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
        protected sendingStatus sendingStatus;
        protected ReadStatus readStatus;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
