package com.sol.client.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfig(SwaggerUiConfigProperties configProperties) {
        configProperties.setDocExpansion("none");
        configProperties.setUseRootPath(true);
        return configProperties;
    }

    @Bean
    public OpenAPI openApi(@Value("${foodtechlab.build.version}") String version) {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("apiKeyAuth",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY).scheme("apiKey")
                                .in(SecurityScheme.In.HEADER).name("X-Auth-Token")))
                .info(new Info()
                        .title("SOL.App")
                        .version(version)
                        .description("Документация к SOL.App"))
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList("apiKeyAuth", Arrays.asList("read", "write")));
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .packagesToScan("ru")
                .build();
    }

}
