package com.github.rostislav-maslov.rcore.notification.domain.pushFeed.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.entity.PushFeedEntity;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.exceptions.PushFeedNotFoundException;
import com.github.rostislav-maslov.rcore.notification.domain.pushFeed.port.PushFeedRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdatePushFeedUseCase extends UseCase<UpdatePushFeedUseCase.InputValues, SingletonEntityOutputValues<PushFeedEntity>> {

    private final PushFeedRepository pushFeedRepository;

    @Override
    public SingletonEntityOutputValues<PushFeedEntity> execute(InputValues inputValues) {

        PushFeedEntity pushFeedEntity = pushFeedRepository.findById(inputValues.getId())
                .orElseThrow(PushFeedNotFoundException::new);

        // Тут изменение данных
        pushFeedEntity.setTitle(inputValues.title);
        pushFeedEntity.setMessage(inputValues.message);
        pushFeedEntity.setParentId(inputValues.parentId);
        pushFeedEntity.setUserId(inputValues.userId);
        pushFeedEntity.setSendingStatus(inputValues.sendingStatus);
        pushFeedEntity.setReadStatus(inputValues.readStatus);
        pushFeedEntity.setPriority(inputValues.priority);
        pushFeedEntity.setError(inputValues.error);
        
        pushFeedEntity = pushFeedRepository.save(pushFeedEntity);

        return SingletonEntityOutputValues.of(pushFeedEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String title;
        protected String message;
        protected String parentId;
        protected String userId;
        protected SendingStatus sendingStatus;
        protected ReadStatus readStatus;
        protected Priority priority;
        protected String error;
    }

}
