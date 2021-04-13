package com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.entity.PushToDeviceEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.exceptions.PushToDeviceNotFoundException;
import com.github.rostislav_maslov.rcore.notification.domain.pushToDevice.port.PushToDeviceRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdatePushToDeviceUseCase extends UseCase<UpdatePushToDeviceUseCase.InputValues, SingletonEntityOutputValues<PushToDeviceEntity>> {

    private final PushToDeviceRepository pushToDeviceRepository;

    @Override
    public SingletonEntityOutputValues<PushToDeviceEntity> execute(InputValues inputValues) {

        PushToDeviceEntity pushToDeviceEntity = pushToDeviceRepository.findById(inputValues.getId())
                .orElseThrow(PushToDeviceNotFoundException::new);

        // Тут изменение данных
        pushToDeviceEntity.setTitle(inputValues.title);
        pushToDeviceEntity.setMessage(inputValues.message);
        pushToDeviceEntity.setDeviceId(inputValues.deviceId);
        pushToDeviceEntity.setPushFeedId(inputValues.pushFeedId);
        pushToDeviceEntity.setParentId(inputValues.parentId);
        pushToDeviceEntity.setUserId(inputValues.userId);
        pushToDeviceEntity.setDeviceType(inputValues.deviceType);
        pushToDeviceEntity.setSendingStatus(inputValues.sendingStatus);
        pushToDeviceEntity.setDeadStatus(inputValues.readStatus);
        
        pushToDeviceEntity = pushToDeviceRepository.save(pushToDeviceEntity);

        return SingletonEntityOutputValues.of(pushToDeviceEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
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

}
