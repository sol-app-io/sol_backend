package com.sol.client.solUser.v1.enpoints;

import com.rcore.rest.api.commons.response.SuccessApiResponse;
import com.sol.client.solUser.v1.api.SignUpEmailRequest;
import com.sol.client.solUser.v1.api.SignUpResponse;
import com.sol.client.solUser.v1.routes.SignUpRoutes;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.entity.SolUserEntity;
import com.sol.domain.solUser.usecases.SignUpByEmailSolUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignUpController  {

    private final SolUserConfig solUserConfig;

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

//        TokenPair tokenPair = authorizationConfig.refreshAccessToken().execute(PasswordAuthorizationUseCase.InputValues.of(
//                request.getEmail(), request.getPassword()
//        )).getTokenPair();
//
//        String accessToken = accessTokenGenerator.generate(
//                AccessTokenData.builder()
//                        .id(tokenPair.getAccessToken().getId())
//                        .credentialId(tokenPair.getAccessToken().getCredential().getId())
//                        .roles(tokenPair.getAccessToken().getCredential().getRoles().stream().map((CredentialEntity.Role role) -> {
//                            return new CredentialDetails.Role(role.getRole().getName());
//                        }).collect(Collectors.toList()))
//                        .createdAt(tokenPair.getAccessToken().getCreatedAt())
//                        .expiredAt(tokenPair.getAccessToken().getExpireAt())
//                        .build()
//        );
//
//        String refreshToken = refreshTokenGenerator.generate(
//                RefreshTokenData.builder()
//                        .id(tokenPair.getRefreshToken().getId())
//                        .credentialId(tokenPair.getRefreshToken().getCredential().getId())
//                        .roles(tokenPair.getRefreshToken().getCredential().getRoles().stream().map(
//                                (CredentialEntity.Role role) -> {
//                                    return new CredentialDetails.Role(role.getRole().getName());
//                                }
//                        ).collect(Collectors.toList()))
//                        .createdAt(tokenPair.getRefreshToken().getCreatedAt())
//                        .expiredAt(tokenPair.getRefreshToken().getExpireAt())
//                        .build()
//        );

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .id(solUserEntity.getId())
                .username(solUserEntity.getUsername())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
                .build();


        return SuccessApiResponse.of(signUpResponse);
    }

//    @PostMapping(value = SignUpRoutes.UPDATE_REFRESH_TOKEN)
//    public SuccessApiResponse<SignUpResponse> refreshToken(@RequestBody UpdateRefreshTokenRequest request) {
//
//        TokenPair tokenPair = tokenConfig.refreshAccessTokenUseCase().execute(RefreshAccessTokenUseCase.InputValues.of(request.getRefreshToken())).getTokenPair();
//        CredentialEntity credentialEntity = tokenPair.getRefreshToken().getCredential();
//
//        SolUserEntity solUserEntity = solUserConfig
//                .meUseCase()
//                .execute(
//                        MeUseCase.InputValues.builder()
//                                .credentialId(credentialEntity.getId())
//                                .build()
//                ).getEntity();
//
//        String accessToken = accessTokenGenerator.generate(
//                AccessTokenData.builder()
//                        .id(tokenPair.getAccessToken().getId())
//                        .credentialId(tokenPair.getAccessToken().getCredential().getId())
//                        .roles(tokenPair.getAccessToken().getCredential().getRoles().stream().map((CredentialEntity.Role role) -> {
//                            return new CredentialDetails.Role(role.getRole().getName());
//                        }).collect(Collectors.toList()))
//                        .createdAt(tokenPair.getAccessToken().getCreatedAt())
//                        .expiredAt(tokenPair.getAccessToken().getExpireAt())
//                        .build()
//        );
//
//        String refreshToken = refreshTokenGenerator.generate(
//                RefreshTokenData.builder()
//                        .id(tokenPair.getRefreshToken().getId())
//                        .credentialId(tokenPair.getRefreshToken().getCredential().getId())
//                        .roles(tokenPair.getRefreshToken().getCredential().getRoles().stream().map(
//                                (CredentialEntity.Role role) -> {
//                                    return new CredentialDetails.Role(role.getRole().getName());
//                                }
//                        ).collect(Collectors.toList()))
//                        .createdAt(tokenPair.getRefreshToken().getCreatedAt())
//                        .expiredAt(tokenPair.getRefreshToken().getExpireAt())
//                        .build()
//        );
//
//        return SuccessApiResponse.of(SignUpResponse.builder()
//                .id(solUserEntity.getId())
//                .username(solUserEntity.getUsername())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build()
//        );
//
//    }
}
