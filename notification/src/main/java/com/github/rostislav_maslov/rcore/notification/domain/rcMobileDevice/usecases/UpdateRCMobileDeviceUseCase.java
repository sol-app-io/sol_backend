package com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.entity.RCMobileDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.exceptions.RCMobileDeviceNotFoundException;
import com.github.rostislav_maslov.rcore.notification.domain.rcMobileDevice.port.RCMobileDeviceRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateRCMobileDeviceUseCase extends UseCase<UpdateRCMobileDeviceUseCase.InputValues, SingletonEntityOutputValues<RCMobileDeviceEntity>> {

    private final RCMobileDeviceRepository rcMobileDeviceRepository;

    @Override
    public SingletonEntityOutputValues<RCMobileDeviceEntity> execute(InputValues inputValues) {

        RCMobileDeviceEntity rcMobileDeviceEntity = rcMobileDeviceRepository.findById(inputValues.getId())
                .orElseThrow(RCMobileDeviceNotFoundException::new);

        // Тут изменение данных
        rcMobileDeviceEntity.setOwnerId(inputValues.ownerId);
        rcMobileDeviceEntity.setDeviceToken(inputValues.deviceToken);
        rcMobileDeviceEntity.setDeviceType(inputValues.deviceType);
        
        rcMobileDeviceEntity = rcMobileDeviceRepository.save(rcMobileDeviceEntity);

        return SingletonEntityOutputValues.of(rcMobileDeviceEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String ownerId;
        protected String deviceToken;
        protected DeviceType deviceType;
    }

}
