package com.sol.domain.repeatTaskConf.usecases;

import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.sol.domain.repeatTaskConf.entity.RepeatTaskConfEntity;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;

/**
 * Поиск сущности
 */
public class FindRepeatTaskConfByIdUseCase extends AbstractFindByIdUseCase<String, RepeatTaskConfEntity, RepeatTaskConfRepository> {

    public FindRepeatTaskConfByIdUseCase(RepeatTaskConfRepository repository) {
        super(repository);
    }
}