package com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.entity.PushNotificationConfEntity;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.exceptions.PushNotificationConfNotFoundException;
import com.github.rostislav_maslov.rcore.notification.domain.pushNotificationConf.port.PushNotificationConfRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdatePushNotificationConfUseCase extends UseCase<UpdatePushNotificationConfUseCase.InputValues, SingletonEntityOutputValues<PushNotificationConfEntity>> {

    private final PushNotificationConfRepository pushNotificationConfRepository;

    @Override
    public SingletonEntityOutputValues<PushNotificationConfEntity> execute(InputValues inputValues) {

        PushNotificationConfEntity pushNotificationConfEntity = pushNotificationConfRepository.findById(inputValues.getId())
                .orElseThrow(PushNotificationConfNotFoundException::new);

        // Тут изменение данных
        pushNotificationConfEntity.setTitle(inputValues.title);
        pushNotificationConfEntity.setMessage(inputValues.message);
        pushNotificationConfEntity.setDeviceId(inputValues.deviceId);
        pushNotificationConfEntity.setPushFeedId(inputValues.pushFeedId);
        pushNotificationConfEntity.setparentId(inputValues.parentId);
        pushNotificationConfEntity.setUserId(inputValues.userId);
        pushNotificationConfEntity.setDeviceType(inputValues.deviceType);
        pushNotificationConfEntity.setSendingStatus(inputValues.sendingStatus);
        pushNotificationConfEntity.setReadStatus(inputValues.readStatus);
        
        pushNotificationConfEntity = pushNotificationConfRepository.save(pushNotificationConfEntity);

        return SingletonEntityOutputValues.of(pushNotificationConfEntity);
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
        protected SendingStatus sendingStatus;
        protected ReadStatus readStatus;
    }

}
