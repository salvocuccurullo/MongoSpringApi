package com.salvocuccurullo.apps.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@ComponentScan
@EnableWebSecurity
public class MongoApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MongoApp.class, args);
    }
}
