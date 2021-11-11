package com.sol.client.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import com.rcore.domain.security.port.AccessChecker;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.event.driven.EventDispatcher;
import com.rcore.rest.api.spring.commons.jackson.datetime.InstantDeserializer;
import com.rcore.rest.api.spring.commons.jackson.datetime.InstantSerializer;
import com.rcore.rest.api.spring.commons.jackson.datetime.LocalDateTimeDeserializer;
import com.rcore.rest.api.spring.commons.jackson.datetime.LocalDateTimeSerializer;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenParser;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenParser;
import com.sol.domain.slot.config.SlotConfig;
import com.sol.domain.slot.port.SlotIdGenerator;
import com.sol.domain.slot.port.SlotRepository;
import com.sol.domain.solUser.config.SolUserConfig;
import com.sol.domain.solUser.port.SolUserIdGenerator;
import com.sol.domain.solUser.port.SolUserRepository;
import com.sol.domain.space.config.SpaceConfig;
import com.sol.domain.space.port.SpaceIdGenerator;
import com.sol.domain.space.port.SpaceRepository;
import com.sol.domain.space.usecases.UpdateTaskCountInSpaceUseCase;
import com.sol.domain.task.config.TaskConfig;
import com.sol.domain.task.port.TaskIdGenerator;
import com.sol.domain.task.port.TaskRepository;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import ru.foodtechlab.lib.auth.integration.core.AccessTokenService;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.impl.AccessCheckerViaAuthService;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.impl.CredentialIdentityServiceViaAuthService;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.roleAccess.CheckAccessServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.roleAccess.RoleAccessServiceFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.authorization.impl.FeignHTTPCredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.commons.AuthorizationRequestInterceptor;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.FeignCredentialServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.impl.FeignHTTPCredentialFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.access.FeignRoleAccessServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.access.impl.FeignHTTPRoleAccessFacade;
import ru.foodtechlab.lib.auth.service.domain.role.port.RoleRepository;
import ru.foodtechlab.lib.auth.service.domain.roleAccess.config.RoleAccessConfig;
import ru.foodtechlab.lib.auth.service.domain.roleAccess.port.RoleAccessIdGenerator;
import ru.foodtechlab.lib.auth.service.domain.roleAccess.port.RoleAccessRepository;
import ru.foodtechlab.lib.auth.service.domain.roleAccess.port.impl.AccessCheckerImpl;
import ru.foodtechlab.lib.auth.service.domain.token.config.TokenLifeCycleConfig;

import java.time.Instant;
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

    @Bean
    public CredentialIdentityService credentialIdentityService(ru.foodtechlab.lib.auth.integration.restapi.feign.authorization.FeignCredentialServiceClient feignCredentialServiceClient) {
        return new CredentialIdentityServiceViaAuthService(new FeignHTTPCredentialServiceFacade(feignCredentialServiceClient));
    }

    @Bean
    public RequestInterceptor requestInterceptor(AccessTokenService accessTokenService) {
        return new AuthorizationRequestInterceptor(accessTokenService);
    }

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
        builder.serializerByType(Instant.class, new InstantSerializer());
        builder.deserializerByType(Instant.class, new InstantDeserializer());
        return builder.build();
    }

    @Bean
    public SolUserConfig solUserConfig(
            SolUserRepository solUserRepository,
            SolUserIdGenerator<?> solUserIdGenerator,
            SpaceConfig spaceConfig,
            ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade credentialServiceFacade,
            ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade roleServiceFacade
    ) {
        return new SolUserConfig(solUserRepository, solUserIdGenerator, spaceConfig, credentialServiceFacade, roleServiceFacade);
    }

    @Bean
    public SpaceConfig spaceConfig(SpaceRepository spaceRepository, SpaceIdGenerator<?> spaceIdGenerator) {
        return new SpaceConfig(spaceRepository, spaceIdGenerator);
    }

    @Bean
    public TaskConfig taskConfig(TaskRepository taskRepository, TaskIdGenerator<?> taskIdGenerator, SolUserConfig solUserConfig, SpaceConfig spaceConfig, SlotRepository slotRepository, SpaceRepository spaceRepository){
        return new TaskConfig(taskRepository, taskIdGenerator, solUserConfig.meUseCase(), spaceConfig.findSpaceByIdUseCase(), spaceConfig.findInboxSpaceByOwnerIdUseCase(), slotRepository, new UpdateTaskCountInSpaceUseCase(spaceRepository, taskRepository));
    }

    @Bean
    public SlotConfig slotConfig(SlotRepository slotRepository, SlotIdGenerator slotIdGenerator, TaskConfig taskConfig, TaskRepository taskRepository){
        return new SlotConfig(slotRepository, slotIdGenerator, taskConfig.recalcSlotsTimeForTaskUseCase(), taskRepository);
    }

    @Bean
    public RoleAccessServiceFacade roleAccessServiceFacade(FeignRoleAccessServiceClient feignRoleAccessServiceClient){
        return new FeignHTTPRoleAccessFacade(feignRoleAccessServiceClient);
    }

    @Bean
    public CredentialServiceFacade credentialServiceFacade(FeignCredentialServiceClient feignCredentialServiceClient){
        return new FeignHTTPCredentialFacade(feignCredentialServiceClient);
    }

    @Bean
    public AccessChecker accessChecker(CheckAccessServiceFacade checkAccessServiceFacade) {
        return new AccessCheckerViaAuthService(checkAccessServiceFacade);
    }
}
