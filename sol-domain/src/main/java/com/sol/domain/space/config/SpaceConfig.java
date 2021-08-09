package com.sol.domain.space.config;

import com.sol.domain.space.usecases.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.domain.space.port.SpaceRepository;

@Accessors(fluent = true)
@Getter
public class SpaceConfig {
    private final CreateSpaceUseCase createSpaceUseCase;
    private final DeleteSpaceUseCase deleteSpaceUseCase;
    private final FindSpaceByIdUseCase findSpaceByIdUseCase;
    private final UpdateSpaceUseCase updateSpaceUseCase;
    private final FindSpaceByOwnerIdUseCase findSpaceByOwnerIdUseCase;
    private final FindInboxSpaceByOwnerIdUseCase findInboxSpaceByOwnerIdUseCase;

    public SpaceConfig(SpaceRepository spaceRepository, SpaceIdGenerator<?> spaceIdGenerator) {
        this.createSpaceUseCase = new CreateSpaceUseCase(spaceRepository, spaceIdGenerator);
        this.deleteSpaceUseCase = new DeleteSpaceUseCase(spaceRepository);
        this.findSpaceByIdUseCase = new FindSpaceByIdUseCase(spaceRepository);
        this.updateSpaceUseCase = new UpdateSpaceUseCase(spaceRepository);
        this.findSpaceByOwnerIdUseCase = new FindSpaceByOwnerIdUseCase(spaceRepository);
        this.findInboxSpaceByOwnerIdUseCase = new FindInboxSpaceByOwnerIdUseCase(spaceRepository);
    }
}
