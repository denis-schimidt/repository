package com.elo7.estudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableWebFluxSecurity
public class Spring5Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring5Application.class, args);
    }
}
