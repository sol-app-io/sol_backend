package com.sol.client.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.auth.authorization.config.AuthorizationConfig;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.auth.confirmationCode.config.ConfirmationCodeConfig;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.auth.confirmationCode.port.impl.ConfirmationCodeGeneratorImpl;
import com.rcore.domain.auth.credential.config.CredentialConfig;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.impl.CredentialIdentityServiceImpl;
import com.rcore.domain.auth.credential.port.impl.CredentialServiceImpl;
import com.rcore.domain.auth.credential.port.impl.PasswordCryptographerImpl;
import com.rcore.domain.auth.role.config.RoleConfig;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.token.config.TokenConfig;
import com.rcore.domain.auth.token.config.TokenLifeCycleConfig;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.CredentialService;
import com.rcore.domain.security.port.TokenParser;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenParser;
import com.rcore.rest.api.spring.security.jwt.access.RSAJwtAccessTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenParser;
import com.rcore.rest.api.spring.security.jwt.refresh.RSAJwtRefreshTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SolClientApplicationConfig {

    @Value("${java.mail.username}")
    private String javaMailUsername;
    @Value("${java.mail.password}")
    private String javaMailPassword;

    @Value("${rcore.security.jwt.key}")
    private String jwtToken;

    private Integer confirmationCodeCodeLength;


    @Bean
    public PictureConfig pictureConfig(
            PictureRepository pictureRepository,
            PictureIdGenerator pictureIdGenerator,
            FileStorage fileStorage
    ) {
        return new PictureConfig(
                pictureRepository,
                pictureIdGenerator,
                fileStorage
        );
    }

    @Bean
    public RoleConfig roleConfig(
            RoleRepository roleRepository,
            RoleIdGenerator roleIdGenerator
    ) {
        return new RoleConfig(roleRepository, roleIdGenerator);
    }

    @Bean
    public CredentialConfig credentialConfig(
            CredentialRepository credentialRepository,
            CredentialIdGenerator credentialIdGenerator,
            RoleConfig roleConfig
    ) {
        return new CredentialConfig(
                credentialRepository,
                credentialIdGenerator,
                new PasswordCryptographerImpl(), roleConfig.findRoleByIdUseCase(), roleConfig.findRoleByNameUseCase());
    }

    @Bean
    public UseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutorImpl();
    }

    @Bean
    public CredentialIdentityService credentialIdentityService(JwtAccessTokenParser jwtAccessTokenParser, AccessTokenRepository accessTokenRepository, CredentialService credentialService) {
        return new CredentialIdentityServiceImpl(jwtAccessTokenParser,accessTokenRepository, credentialService);
    }

    @Bean
    public CredentialService credentialService(
            CredentialRepository credentialRepository, RoleRepository roleRepository
    ) {
        return new CredentialServiceImpl(
                credentialRepository, roleRepository
        );
    }

    @Bean
    public JwtRefreshTokenParser jwtRefreshTokenParser() {
        return new JwtRefreshTokenParser(new ObjectMapper(), new RSAJwtRefreshTokenGenerator(jwtToken));
    }

    @Bean
    public JwtAccessTokenParser jwtAccessTokenParser() {
        return new JwtAccessTokenParser(new ObjectMapper(), new RSAJwtAccessTokenGenerator(jwtToken));
    }

    @Bean
    public TokenLifeCycleConfig tokenLifeCycleConfig() {
        TokenLifeCycleConfig tokenLifeCycleConfig = new TokenLifeCycleConfig();
        return tokenLifeCycleConfig;
    }

    @Bean
    public TokenConfig tokenConfig(
            AccessTokenRepository accessTokenRepository,
            AccessTokenIdGenerator accessTokenIdGenerator,
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenIdGenerator refreshTokenIdGenerator,
            JwtRefreshTokenParser jwtRefreshTokenParser,
            CredentialRepository credentialRepository,
            TokenLifeCycleConfig tokenLifeCycleConfig
    ) {
        return new TokenConfig(
                accessTokenRepository,
                accessTokenIdGenerator,
                refreshTokenRepository,
                refreshTokenIdGenerator,
                new TokenSaltGeneratorImpl(),
                jwtRefreshTokenParser,
                credentialRepository,
                tokenLifeCycleConfig
        );
    }

    @Bean
    public ConfirmationCodeConfig confirmationCodeConfig(
            ConfirmationCodeRepository confirmationCodeRepository, ConfirmationCodeIdGenerator confirmationCodeIdGenerator
    ) {
        return new ConfirmationCodeConfig(
                confirmationCodeRepository, confirmationCodeIdGenerator, new ConfirmationCodeGeneratorImpl(confirmationCodeCodeLength)
        );
    }

    @Bean
    public AuthorizationConfig authorizationConfig(
            AuthorizationRepository authorizationRepository,
            AuthorizationIdGenerator authorizationIdGenerator,
            ConfirmationCodeRepository confirmationCodeRepository,
            CredentialConfig credentialConfig,
            TokenConfig tokenConfig,
            ConfirmationCodeConfig confirmationCodeConfig,
            JwtAccessTokenParser jwtAccessTokenParser,
            AccessTokenRepository accessTokenRepository,
            RefreshTokenRepository refreshTokenRepository,
            CredentialRepository credentialRepository
    ) {
        return new AuthorizationConfig(
                authorizationRepository,
                authorizationIdGenerator,
                confirmationCodeRepository,
                credentialConfig.findCredentialByIdUseCase(),
                tokenConfig.createAccessTokenUseCase(),
                tokenConfig.createRefreshTokenUseCase(),
                confirmationCodeConfig.createConfirmationCodeUseCase(),
                credentialConfig.findCredentialByPhoneUseCase(),
                credentialConfig.findCredentialByEmailUseCase(),
                jwtAccessTokenParser, accessTokenRepository, refreshTokenRepository,
                credentialRepository,
                new PasswordCryptographerImpl()
        );
    }


}
