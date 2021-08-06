package com.sol.client.space.v1.mapper;

import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.space.v1.api.response.SpaceResponse;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.space.entity.SpaceEntity;

import java.util.ArrayList;

public class SpaceApiMapper {
    public SpaceResponse mapper(SpaceEntity spaceEntity){
        return SpaceResponse.builder()
                .id(spaceEntity.getId())
                .icon(spaceEntity.getIcon())
                .title(spaceEntity.getTitle())
                .sortNum(spaceEntity.getSortNum())
                .tasks(new ArrayList<>())
                .build();
    }
}
