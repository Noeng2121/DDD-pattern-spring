package com.example.dddpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class DddPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddPatternApplication.class, args);
    }

}
