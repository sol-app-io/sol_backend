package com.sol.client.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.commons.port.FileStorage;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.domain.security.port.CredentialService;
import com.rcore.rest.api.spring.commons.jackson.datetime.LocalDateTimeDeserializer;
import com.rcore.rest.api.spring.commons.jackson.datetime.LocalDateTimeSerializer;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenParser;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenParser;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.space.config.SpaceConfig;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.domain.space.port.SpaceRepository;
import com.sol.domain.space.usecases.CreateSpaceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import ru.foodtechlab.lib.auth.integration.core.authorization.impl.CredentialIdentityServiceViaAuthService;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.authorization.impl.FeignHTTPCredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.FeignCredentialServiceClient;
import ru.foodtechlab.lib.auth.service.domain.authorization.config.AuthorizationConfig;
import ru.foodtechlab.lib.auth.service.domain.authorization.port.AuthorizationIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.authorization.port.AuthorizationRepository;
import ru.foodtechlab.lib.auth.service.domain.confirmationCode.config.ConfirmationCodeConfig;
import ru.foodtechlab.lib.auth.service.domain.confirmationCode.port.ConfirmationCodeIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.confirmationCode.port.ConfirmationCodeRepository;
import ru.foodtechlab.lib.auth.service.domain.confirmationCode.port.impl.ConfirmationCodeGeneratorImpl;
import ru.foodtechlab.lib.auth.service.domain.credential.config.CredentialConfig;
import ru.foodtechlab.lib.auth.service.domain.credential.port.CredentialIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.credential.port.CredentialRepository;
import ru.foodtechlab.lib.auth.service.domain.credential.port.impl.CredentialIdentityServiceImpl;
import ru.foodtechlab.lib.auth.service.domain.credential.port.impl.CredentialServiceImpl;
import ru.foodtechlab.lib.auth.service.domain.credential.port.impl.PasswordCryptographerImpl;
import ru.foodtechlab.lib.auth.service.domain.role.config.RoleConfig;
import ru.foodtechlab.lib.auth.service.domain.role.port.RoleIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.role.port.RoleRepository;
import ru.foodtechlab.lib.auth.service.domain.token.config.TokenConfig;
import ru.foodtechlab.lib.auth.service.domain.token.config.TokenLifeCycleConfig;
import ru.foodtechlab.lib.auth.service.domain.token.port.AccessTokenIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.token.port.AccessTokenRepository;
import ru.foodtechlab.lib.auth.service.domain.token.port.RefreshTokenIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.token.port.RefreshTokenRepository;
import ru.foodtechlab.lib.auth.service.domain.token.port.impl.TokenSaltGeneratorImpl;

import java.time.LocalDateTime;

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
    public UseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutorImpl();
    }

//    @Bean
//    public CredentialIdentityService credentialIdentityService(ru.foodtechlab.lib.auth.integration.restapi.feign.authorization.FeignCredentialServiceClient feignCredentialServiceClient) {
//        return new CredentialIdentityServiceViaAuthService(new FeignHTTPCredentialServiceFacade(feignCredentialServiceClient));
//    }

    @Bean
    public TokenLifeCycleConfig tokenLifeCycleConfig() {
        return new TokenLifeCycleConfig();
    }

    @Bean
    public JwtRefreshTokenParser jwtRefreshTokenParser(ObjectMapper objectMapper, JwtRefreshTokenGenerator jwtRefreshTokenGenerator) {
        return new JwtRefreshTokenParser(objectMapper, jwtRefreshTokenGenerator);
    }

    @Bean
    public JwtAccessTokenParser jwtAccessTokenParser(ObjectMapper objectMapper, JwtAccessTokenGenerator jwtAccessTokenGenerator) {
        return new JwtAccessTokenParser(objectMapper, jwtAccessTokenGenerator);
    }

    @Bean
    public JwtRefreshTokenGenerator jwtRefreshTokenGenerator(ObjectMapper objectMapper) {
        return new JwtRefreshTokenGenerator(objectMapper);
    }

    @Bean
    public JwtAccessTokenGenerator jwtAccessTokenGenerator(ObjectMapper objectMapper) {
        return new JwtAccessTokenGenerator(objectMapper);
    }



    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
        return builder.build();
    }

    @Bean
    public SolUserConfig solUserConfig(
            SolUserRepository solUserRepository,
            SolUserIdGenerator<?> solUserIdGenerator,
            SpaceConfig spaceConfig,
            ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade credentialServiceFacade,
            ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade roleServiceFacade
    ){
        return new SolUserConfig(solUserRepository, solUserIdGenerator, spaceConfig, credentialServiceFacade, roleServiceFacade);
    }

    @Bean
    public SpaceConfig spaceConfig(SpaceRepository spaceRepository, SpaceIdGenerator<?> spaceIdGenerator){
        return new SpaceConfig(spaceRepository, spaceIdGenerator);
    }


}
