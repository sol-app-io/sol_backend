package com.sol.client.solUser.v1.mapper;

import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.domain.solUser.entity.SolUserEntity;

public class SolUserMapper {
    public SignUpResponse mapper(SolUserEntity solUserEntity){
        return SignUpResponse.builder().id(solUserEntity.getId()).build();
    }
}
