package com.sol.client.solUser.v1.enpoints;

import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.authorization.usecases.PasswordAuthorizationUseCase;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.CredentialDetails;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenGenerator;
import com.sol.client.solUser.v1.api.SignUpEmailRequest;
import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.solUser.v1.mapper.SolUserMapper;
import com.sol.client.solUser.v1.routes.SignUpRoutes;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.Credential;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.SignUpByEmailSolUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final SolUserConfig solUserConfig;
    private final AuthorizationConfig authorizationConfig;
    private final SolUserMapper mapper = new SolUserMapper();
    private final TokenGenerator<AccessTokenData> accessTokenGenerator;
    private final TokenGenerator<RefreshTokenData> refreshTokenGenerator;


    @PostMapping(value = SignUpRoutes.EMAIL)
    public SuccessApiResponse<SignUpResponse> email(@RequestBody SignUpEmailRequest request) {
        SolUserEntity solUserEntity = solUserConfig
                .signUpByEmailSolUserUseCase()
                .execute(
                        SignUpByEmailSolUserUseCase.InputValues.builder()
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .build()
                ).getEntity();
        TokenPair tokenPair = authorizationConfig.passwordAuthorizationUseCase().execute(PasswordAuthorizationUseCase.InputValues.of(
                request.getEmail(), request.getPassword()
        )).getTokenPair();

        String accessToken = accessTokenGenerator.generate(
                AccessTokenData.builder()
                        .id(tokenPair.getAccessToken().getId())
                        .credentialId(tokenPair.getAccessToken().getCredential().getId())
                        .roles(tokenPair.getAccessToken().getCredential().getRoles().stream().map((CredentialEntity.Role role) -> {
                            return new CredentialDetails.Role(role.getRole().getName());
                        }).collect(Collectors.toList()))
                        .createdAt(tokenPair.getAccessToken().getCreatedAt())
                        .expiredAt(tokenPair.getAccessToken().getExpireAt())
                        .build()
        );

        String refreshToken = refreshTokenGenerator.generate(
                RefreshTokenData.builder()
                        .id(tokenPair.getRefreshToken().getId())
                        .credentialId(tokenPair.getRefreshToken().getCredential().getId())
                        .roles(tokenPair.getRefreshToken().getCredential().getRoles().stream().map(
                                (CredentialEntity.Role role) -> {
                                    return new CredentialDetails.Role(role.getRole().getName());
                                }
                        ).collect(Collectors.toList()))
                        .createdAt(tokenPair.getRefreshToken().getCreatedAt())
                        .expiredAt(tokenPair.getRefreshToken().getExpireAt())
                        .build()
        );

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .id(solUserEntity.getId())
                .username(solUserEntity.getUsername())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();


        return SuccessApiResponse.of(signUpResponse);
    }
}
