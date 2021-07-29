package com.sol.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication(scanBasePackages = {"com.rcore", "ru.foodtechlab", "com.sol"}, exclude = {EmbeddedMongoAutoConfiguration.class})
@EnableMongoRepositories
@EnableFeignClients(basePackages = {"com.rcore", "ru.foodtechlab", "com.sol"})
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SolClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolClientApplication.class, args);
    }

}
