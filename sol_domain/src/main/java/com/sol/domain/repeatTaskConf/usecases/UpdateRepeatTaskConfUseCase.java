package com.sol.domain.repeatTaskConf.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import com.sol.domain.repeatTaskConf.exceptions.RepeatTaskConfNotFoundException;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateRepeatTaskConfUseCase extends UseCase<UpdateRepeatTaskConfUseCase.InputValues, SingletonEntityOutputValues<RepeatTaskConfEntity>> {

    private final RepeatTaskConfRepository repeatTaskConfRepository;

    @Override
    public SingletonEntityOutputValues<RepeatTaskConfEntity> execute(InputValues inputValues) {

        RepeatTaskConfEntity repeatTaskConfEntity = repeatTaskConfRepository.findById(inputValues.getId())
                .orElseThrow(RepeatTaskConfNotFoundException::new);

        // Тут изменение данных
        repeatTaskConfEntity.setOwnerId(inputValues.ownerId);
        repeatTaskConfEntity.setCreatedFromTaskId(inputValues.createdFromTaskId);
        repeatTaskConfEntity.setSpaceId(inputValues.spaceId);
        repeatTaskConfEntity.setViewIds(inputValues.viewIds);
        repeatTaskConfEntity.setLastTaskCreatedFromConfId(inputValues.lastTaskCreatedFromConfId);
        repeatTaskConfEntity.setRepeatType(inputValues.repeatType);
        repeatTaskConfEntity.setRepeatValue(inputValues.repeatValue);
        repeatTaskConfEntity.setTasksCreatedFromConf(inputValues.tasksCreatedFromConf);
        
        repeatTaskConfEntity = repeatTaskConfRepository.save(repeatTaskConfEntity);

        return SingletonEntityOutputValues.of(repeatTaskConfEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;

        //указываются данные которые необходимо изменить
        protected String ownerId;
        protected String createdFromTaskId;
        protected String spaceId;
        protected List&lt;String&gt; viewIds;
        protected String lastTaskCreatedFromConfId;
        protected RepeatType repeatType;
        protected Long repeatValue;
        protected List&lt;String&gt; tasksCreatedFromConf;
    }

}
