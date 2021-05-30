package com.sol.client.solUser.v1.enpoints;

import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.config.TokenConfig;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.usecases.RefreshAccessTokenUseCase;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.CredentialPrincipal;
import com.rcore.rest.api.spring.security.CurrentCredential;
import com.sol.client.solUser.v1.api.SignUpEmailRequest;
import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.solUser.v1.api.UpdateRefreshTokenRequest;
import com.sol.client.solUser.v1.routes.SignUpRoutes;
import com.sol.client.solUser.v1.routes.SolUserRoutes;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.MeUseCase;
import com.sol.domain.solUser.usecases.SignUpByEmailSolUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SolUserController {

    private final SolUserConfig solUserConfig;

    @GetMapping(value = SolUserRoutes.ME)
    public SuccessApiResponse<SignUpResponse> me(
            @ApiIgnore @CurrentCredential CredentialPrincipal credentialPrincipal) {

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
