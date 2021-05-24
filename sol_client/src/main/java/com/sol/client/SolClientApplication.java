package com.sol.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication(scanBasePackages = {"com.rcore", "com.sol"}, exclude = {EmbeddedMongoAutoConfiguration.class})
//@EnableFeignClients(basePackages = {"com.sol.infrastructure"})
@EnableMongoRepositories
public class SolClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolClientApplication.class, args);
    }

}
