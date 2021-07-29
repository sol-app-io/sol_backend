package com.sol.client.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Sol API docs")
                        .version("v1")
                        .build())
                .securityContexts(securityContexts())
                .groupName("v1")
                .securitySchemes(securitySchemes())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sol"))
                .paths(PathSelectors.any())
                .build();
    }

    public static List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }

    public static List<SecurityReference> securityReferences() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Arrays.asList(new SecurityReference("x-auth-token", authorizationScopes), new SecurityReference("x-device-token", authorizationScopes), new SecurityReference("x-device-type", authorizationScopes));
    }

    public static List<SecurityScheme> securitySchemes() {
        return Arrays.asList(new ApiKey("x-auth-token", "X-Auth-Token", In.HEADER.name()), new ApiKey("x-device-token", "X-Device-Token", In.HEADER.name()), new ApiKey("x-device-type", "X-Device-Type", In.HEADER.name()));
    }
}
