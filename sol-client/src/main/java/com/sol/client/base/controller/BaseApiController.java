package com.sol.client.base.controller;

import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseApiController {
    protected abstract SolUserConfig solUserConfig();

    protected SolUserEntity getCurrentUser(CredentialPrincipal credentialPrincipal) {
        SolUserEntity solUserEntity = solUserConfig().meUseCase().execute(
                MeUseCase.InputValues.builder().credentialId(credentialPrincipal.getId()).build()
        ).getEntity();
        return solUserEntity;
    }
}
