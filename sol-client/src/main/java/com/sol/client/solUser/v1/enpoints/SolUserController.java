package com.sol.client.solUser.v1.enpoints;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.solUser.v1.routes.SolUserRoutes;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SolUserController {

    private final SolUserConfig solUserConfig;

    @GetMapping(value = SolUserRoutes.ME)
    public SuccessApiResponse<SignUpResponse> me(
             @CurrentCredential CredentialPrincipal credentialPrincipal) {

        SolUserEntity solUserEntity = solUserConfig
                .meUseCase()
                .execute(
                        MeUseCase.InputValues.builder()
                                .credentialId(credentialPrincipal.getId())
                                .build()
                ).getEntity();

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .id(solUserEntity.getId())
                .username(solUserEntity.getUsername())
                .build();

        return SuccessApiResponse.of(signUpResponse);
    }


}
