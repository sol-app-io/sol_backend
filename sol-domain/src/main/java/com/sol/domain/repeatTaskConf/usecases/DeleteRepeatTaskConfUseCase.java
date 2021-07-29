package com.sol.domain.repeatTaskConf.usecases;

import com.rcore.domain.commons.usecase.AbstractDeleteUseCase;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;

/**
 * Удаление сущности
 */
public class DeleteRepeatTaskConfUseCase extends AbstractDeleteUseCase<String, RepeatTaskConfRepository> {

    public DeleteRepeatTaskConfUseCase(RepeatTaskConfRepository repository) {
        super(repository);
    }
}
