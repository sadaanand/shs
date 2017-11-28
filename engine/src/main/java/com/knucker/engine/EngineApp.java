package com.knucker.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= {"com.knucker.engine"})
@SpringBootApplication
public class EngineApp {

    public static void main(String[] args) {
        SpringApplication.run(EngineApp.class, args);
    }
}