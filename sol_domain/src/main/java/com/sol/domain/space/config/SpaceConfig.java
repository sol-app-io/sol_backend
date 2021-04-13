package com.sol.domain.space.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.domain.space.port.SpaceRepository;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import com.sol.domain.space.usecases.DeleteSpaceUseCase;
import com.sol.domain.space.usecases.FindSpaceByIdUseCase;
import com.sol.domain.space.usecases.UpdateSpaceUseCase;

@Accessors(fluent = true)
@Getter
public class SpaceConfig {
    private final CreateSpaceUseCase createSpaceUseCase;
    private final DeleteSpaceUseCase deleteSpaceUseCase;
    private final FindSpaceByIdUseCase findSpaceByIdUseCase;
    private final UpdateSpaceUseCase updateSpaceUseCase;

    public SpaceConfig(SpaceRepository spaceRepository, SpaceIdGenerator<?> spaceIdGenerator) {
        this.createSpaceUseCase = new CreateSpaceUseCase(spaceRepository, spaceIdGenerator);
        this.deleteSpaceUseCase = new DeleteSpaceUseCase(spaceRepository);
        this.findSpaceByIdUseCase = new FindSpaceByIdUseCase(spaceRepository);
        this.updateSpaceUseCase = new UpdateSpaceUseCase(spaceRepository);
    }
}
