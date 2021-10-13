package com.sol.domain.space.usecases;

import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import com.sol.domain.slot.entity.SlotEntity;
import com.sol.domain.slot.exceptions.SlotNotFoundException;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.space.entity.SpaceEntity;
import com.sol.domain.space.exceptions.SpaceNotFoundException;
import com.sol.domain.space.port.SpaceRepository;
import com.sol.domain.task.entity.TaskStatus;
import com.sol.domain.task.port.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Обновление сущности
 */
@RequiredArgsConstructor
public class UpdateTaskCountInSpaceUseCase extends UseCase<UpdateTaskCountInSpaceUseCase.InputValues, SingletonEntityOutputValues<SpaceEntity>> {

    private final SpaceRepository spaceRepository;
    private final TaskRepository taskRepository;

    @Override
    public SingletonEntityOutputValues<SpaceEntity> execute(InputValues inputValues) {

        SpaceEntity spaceEntity = spaceRepository.findById(inputValues.getId())
                .orElseThrow(SpaceNotFoundException::new);
        List<TaskStatus> statuses = new ArrayList<>();
        statuses.add(TaskStatus.OPEN);

        spaceEntity.setCountTask(taskRepository.countBySpaceId(inputValues.id, statuses));
        spaceEntity = spaceRepository.save(spaceEntity);

        return SingletonEntityOutputValues.of(spaceEntity);
    }

    @AllArgsConstructor(staticName = "of")
    @Getter
    public static class InputValues implements UseCase.InputValues {
        //Сущность которую требуется обновить
        protected String id;
    }

}
