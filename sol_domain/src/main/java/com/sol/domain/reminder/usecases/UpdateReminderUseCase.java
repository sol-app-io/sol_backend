package com.sol.domain.reminder.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.reminder.entity.ReminderEntity;
import com.sol.domain.reminder.exceptions.ReminderNotFoundException;
import com.sol.domain.reminder.port.ReminderRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateReminderUseCase extends UseCase<UpdateReminderUseCase.InputValues, SingletonEntityOutputValues<ReminderEntity>> {

    private final ReminderRepository reminderRepository;

    @Override
    public SingletonEntityOutputValues<ReminderEntity> execute(InputValues inputValues) {

        ReminderEntity reminderEntity = reminderRepository.findById(inputValues.getId())
                .orElseThrow(ReminderNotFoundException::new);

        // Тут изменение данных
        reminderEntity.setOwnerId(inputValues.ownerId);
        reminderEntity.setTaskId(inputValues.taskId);
        reminderEntity.setSpaceId(inputValues.spaceId);
        reminderEntity.setViewIds(inputValues.viewIds);
        reminderEntity.setReminderTime(inputValues.reminderTime);
        
        reminderEntity = reminderRepository.save(reminderEntity);

        return SingletonEntityOutputValues.of(reminderEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String ownerId;
        protected String taskId;
        protected String spaceId;
        protected List&lt;String&gt; viewIds;
        protected DateTime reminderTime;
    }

}
