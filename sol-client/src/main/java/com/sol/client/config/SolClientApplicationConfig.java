package com.sol.client.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.domain.commons.usecase.impl.UseCaseExecutorImpl;
import com.rcore.domain.security.port.AccessChecker;
import com.rcore.domain.security.port.CredentialIdentityService;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.access.JwtAccessTokenParser;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenGenerator;
import com.rcore.rest.api.spring.security.jwt.refresh.JwtRefreshTokenParser;
import com.sol.domain.backgroundTaskForView.config.BackgroundTaskForViewConfig;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewIdGenerator;
import com.sol.domain.backgroundTaskForView.port.BackgroundTaskForViewRepository;
import com.sol.domain.category.config.CategoryConfig;
import com.sol.domain.category.port.CategoryIdGenerator;
import com.sol.domain.category.port.CategoryRepository;
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
import com.sol.domain.taskInView.config.TaskInViewConfig;
import com.sol.domain.taskInView.port.TaskInViewIdGenerator;
import com.sol.domain.taskInView.port.TaskInViewRepository;
import com.sol.domain.taskInView.usecases.DeleteAllTaskInViewByTaskUseCase;
import com.sol.domain.viewTemplate.config.ViewTemplateConfig;
import com.sol.domain.viewTemplate.port.ViewTemplateIdGenerator;
import com.sol.domain.viewTemplate.port.ViewTemplateRepository;
import com.sol.domain.viewUser.config.ViewUserConfig;
import com.sol.domain.viewUser.port.ViewUserIdGenerator;
import com.sol.domain.viewUser.port.ViewUserRepository;
import com.sol.domain.viewsSort.config.ViewsSortConfig;
import com.sol.domain.viewsSort.port.ViewsSortIdGenerator;
import com.sol.domain.viewsSort.port.ViewsSortRepository;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import ru.foodtechlab.lib.auth.integration.core.AccessTokenService;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.AuthCredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.impl.AccessCheckerViaAuthService;
import ru.foodtechlab.lib.auth.integration.core.authorizartion.impl.CredentialIdentityServiceViaAuthService;
import ru.foodtechlab.lib.auth.integration.core.credential.CredentialServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.role.RoleServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.roleAccess.CheckAccessServiceFacade;
import ru.foodtechlab.lib.auth.integration.core.roleAccess.RoleAccessServiceFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.authorization.FeignCredentialServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.commons.AuthorizationRequestInterceptor;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.FeignCredentialAndInitServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.credential.impl.FeignHTTPCredentialFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.FeignRoleServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.access.FeignRoleAccessServiceClient;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.access.impl.FeignHTTPRoleAccessFacade;
import ru.foodtechlab.lib.auth.integration.restapi.feign.role.impl.FeignHTTPRoleFacade;
import ru.foodtechlab.lib.auth.service.domain.token.config.TokenLifeCycleConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,
                                               Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() &&
                (StringUtils.hasText(basePath) ||
                        ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
            WebEndpointsSupplier webEndpointsSupplier,
            ServletEndpointsSupplier servletEndpointsSupplier,
            ControllerEndpointsSupplier controllerEndpointsSupplier,
            EndpointMediaTypes endpointMediaTypes,
            CorsEndpointProperties corsProperties,
            WebEndpointProperties webEndpointProperties,
            Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(
                webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints,
                endpointMediaTypes, corsProperties.toCorsConfiguration(),
                new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping, null);
    }

    @Bean
    public UseCaseExecutor useCaseExecutor() {
        return new UseCaseExecutorImpl();
    }

    @Bean
    public CredentialIdentityService credentialIdentityService(AuthCredentialServiceFacade authCredentialServiceFacade) {
        return new CredentialIdentityServiceViaAuthService(authCredentialServiceFacade);
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
    public RoleServiceFacade roleServiceFacade(FeignRoleServiceClient httpClient) {
        return new FeignHTTPRoleFacade(httpClient);
    }

    @Bean
    public SolUserConfig solUserConfig(
            SolUserRepository solUserRepository,
            SolUserIdGenerator<?> solUserIdGenerator,
            SpaceConfig spaceConfig,
            CredentialServiceFacade credentialServiceFacade,
            RoleServiceFacade roleServiceFacade,
            ViewUserConfig viewUserConfig
    ) {
        return new SolUserConfig(solUserRepository, solUserIdGenerator, spaceConfig, credentialServiceFacade, roleServiceFacade, viewUserConfig);
    }

    @Bean
    public SpaceConfig spaceConfig(SpaceRepository spaceRepository, SpaceIdGenerator<?> spaceIdGenerator) {
        return new SpaceConfig(spaceRepository, spaceIdGenerator);
    }

    @Bean
    public TaskConfig taskConfig(
            TaskRepository taskRepository,
            TaskIdGenerator<?> taskIdGenerator,
            SolUserConfig solUserConfig,
            SpaceConfig spaceConfig,
            SlotRepository slotRepository,
            SpaceRepository spaceRepository,
            ViewUserRepository viewUserRepository,
            TaskInViewRepository taskInViewRepository,
            BackgroundTaskForViewConfig backgroundTaskForViewConfig
            ) {
        return new TaskConfig(
                taskRepository,
                taskIdGenerator,
                solUserConfig.meUseCase(),
                spaceConfig.findSpaceByIdUseCase(),
                spaceConfig.findInboxSpaceByOwnerIdUseCase(),
                slotRepository,
                new UpdateTaskCountInSpaceUseCase(spaceRepository, taskRepository),
                viewUserRepository,
                backgroundTaskForViewConfig.createBackgroundTaskForViewUseCase(),
                new DeleteAllTaskInViewByTaskUseCase(taskInViewRepository)
        );
    }

    @Bean
    public SlotConfig slotConfig(SlotRepository slotRepository, SlotIdGenerator slotIdGenerator, TaskConfig taskConfig, TaskRepository taskRepository) {
        return new SlotConfig(slotRepository, slotIdGenerator, taskConfig.recalcSlotsTimeForTaskUseCase(), taskRepository);
    }

    @Bean
    public RoleAccessServiceFacade roleAccessServiceFacade(FeignRoleAccessServiceClient feignRoleAccessServiceClient) {
        return new FeignHTTPRoleAccessFacade(feignRoleAccessServiceClient);
    }

    @Bean
    public CredentialServiceFacade credentialServiceFacade(FeignCredentialAndInitServiceClient httpClient) {
        return new FeignHTTPCredentialFacade(httpClient);
    }



    @Bean
    public AccessChecker accessChecker(CheckAccessServiceFacade checkAccessServiceFacade) {
        return new AccessCheckerViaAuthService(checkAccessServiceFacade);
    }

    @Bean
    public CategoryConfig categoryConfig(CategoryRepository categoryRepository, CategoryIdGenerator categoryIdGenerator) {
        return new CategoryConfig(categoryRepository, categoryIdGenerator);
    }

    @Bean
    public ViewUserConfig viewUserConfig(
            ViewUserRepository viewUserRepository,
            ViewUserIdGenerator<?> viewUserIdGenerator,
            TaskInViewRepository taskInViewRepository,
            SolUserRepository solUserRepository,
            ViewTemplateRepository viewTemplateRepository,
            ViewsSortRepository viewsSortRepository,
            BackgroundTaskForViewRepository backgroundTaskForViewRepository,
            ViewsSortConfig viewsSortConfig,
            TaskInViewConfig taskInViewConfig,
            BackgroundTaskForViewConfig backgroundTaskForViewConfig
    ) {
        return new ViewUserConfig(
                viewUserRepository,
                viewUserIdGenerator,
                taskInViewRepository,
                solUserRepository,
                viewTemplateRepository,
                viewsSortRepository,
                backgroundTaskForViewRepository,
                taskInViewConfig.deleteAllTaskInViewByViewUseCase(),
                viewsSortConfig.findViewsSortByUserIdUseCase(),
                viewsSortConfig.updateViewsSortUseCase(),
                backgroundTaskForViewConfig.createBackgroundViewForViewUseCase());
    }

    @Bean
    public BackgroundTaskForViewConfig backgroundTaskForViewConfig(
            BackgroundTaskForViewRepository backgroundTaskForViewRepository,
            BackgroundTaskForViewIdGenerator backgroundTaskForViewIdGenerator,
            TaskRepository taskRepository,
            ViewUserRepository viewUserRepository,
            TaskInViewConfig taskInViewConfig
    ) {
        return new BackgroundTaskForViewConfig(
                backgroundTaskForViewRepository,
                backgroundTaskForViewIdGenerator,
                taskRepository,
                viewUserRepository,
                taskInViewConfig.createTaskInViewUseCase(),
                taskInViewConfig.deleteTaskInViewUseCase());
    }

    @Bean
    public TaskInViewConfig taskInViewConfig(TaskInViewRepository taskInViewRepository, TaskInViewIdGenerator taskInViewIdGenerator, TaskRepository taskRepository) {
        return new TaskInViewConfig(taskInViewRepository, taskInViewIdGenerator, taskRepository);
    }

    @Bean
    public ViewsSortConfig viewsSortConfig(
            ViewsSortRepository viewsSortRepository,
            ViewUserRepository viewUserRepository,
            ViewsSortIdGenerator viewsSortIdGenerator) {
        return new ViewsSortConfig(
                viewsSortRepository, viewUserRepository, viewsSortIdGenerator
        );
    }

    @Bean
    public ViewTemplateConfig viewTemplateConfig(ViewTemplateRepository viewTemplateRepository,
                                                 ViewTemplateIdGenerator viewTemplateIdGenerator, ViewUserConfig viewUserConfig) {
        return new ViewTemplateConfig(viewTemplateRepository, viewTemplateIdGenerator, viewUserConfig.removeAllViewByTemplateForAllUserUseCase());
    }
}
