package com.sol.domain.solUser.usecases;

import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.space.entity.SpaceEntity;

public final class CheckAccess {

    public static Boolean checkAccess(SolUserEntity solUserEntity, SpaceEntity spaceEntity) {
        if(solUserEntity.getId().equals(spaceEntity.getOwnerId())) return true;
        return false;
    }
}
