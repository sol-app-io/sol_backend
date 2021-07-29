package com.sol.domain.repeatTaskConf.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfIdGenerator;
import com.sol.domain.repeatTaskConf.port.RepeatTaskConfRepository;
import com.sol.domain.repeatTaskConf.usecases.CreateRepeatTaskConfUseCase;
import com.sol.domain.repeatTaskConf.usecases.DeleteRepeatTaskConfUseCase;
import com.sol.domain.repeatTaskConf.usecases.FindRepeatTaskConfByIdUseCase;
import com.sol.domain.repeatTaskConf.usecases.UpdateRepeatTaskConfUseCase;

@Accessors(fluent = true)
@Getter
public class RepeatTaskConfConfig {
    private final CreateRepeatTaskConfUseCase createRepeatTaskConfUseCase;
    private final DeleteRepeatTaskConfUseCase deleteRepeatTaskConfUseCase;
    private final FindRepeatTaskConfByIdUseCase findRepeatTaskConfByIdUseCase;
    private final UpdateRepeatTaskConfUseCase updateRepeatTaskConfUseCase;

    public RepeatTaskConfConfig(RepeatTaskConfRepository repeatTaskConfRepository, RepeatTaskConfIdGenerator<?> repeatTaskConfIdGenerator) {
        this.createRepeatTaskConfUseCase = new CreateRepeatTaskConfUseCase(repeatTaskConfRepository, repeatTaskConfIdGenerator);
        this.deleteRepeatTaskConfUseCase = new DeleteRepeatTaskConfUseCase(repeatTaskConfRepository);
        this.findRepeatTaskConfByIdUseCase = new FindRepeatTaskConfByIdUseCase(repeatTaskConfRepository);
        this.updateRepeatTaskConfUseCase = new UpdateRepeatTaskConfUseCase(repeatTaskConfRepository);
    }
}
