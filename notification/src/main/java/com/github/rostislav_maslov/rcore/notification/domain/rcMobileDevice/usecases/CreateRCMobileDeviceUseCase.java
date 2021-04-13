package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases;

import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.entity.*;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceIdGenerator;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceRepository;

/**
 * Создание сущности
 */
public class CreateRCMobileDeviceUseCase extends AbstractCreateUseCase<RCMobileDeviceEntity, RCMobileDeviceIdGenerator, RCMobileDeviceRepository, CreateRCMobileDeviceUseCase.InputValues> {


    public CreateRCMobileDeviceUseCase(RCMobileDeviceRepository repository, RCMobileDeviceIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<RCMobileDeviceEntity> execute(InputValues inputValues) {

        validate(inputValues);

        RCMobileDeviceEntity rcMobileDeviceEntity = new RCMobileDeviceEntity(idGenerator.generate());
       
        // Происходит заполнение всех полей 
        rcMobileDeviceEntity.setOwnerId(inputValues.ownerId);
        rcMobileDeviceEntity.setDeviceToken(inputValues.deviceToken);
        rcMobileDeviceEntity.setDeviceType(inputValues.deviceType);

        rcMobileDeviceEntity = repository.save(rcMobileDeviceEntity);

        return SingletonEntityOutputValues.of(rcMobileDeviceEntity);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class InputValues implements UseCase.InputValues {
        // перечисление полей необходимых для создания сущности
        protected String ownerId;
        protected String deviceToken;
        protected DeviceType deviceType;

    }

    /**
     * Валидация входящий данных
     * @param inputValues
     */
    private void validate(InputValues inputValues) {

    }

}
