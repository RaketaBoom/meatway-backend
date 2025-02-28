package com.example.meatwaybackend;

import org.springframework.boot.SpringApplication;

public class TestMeatwayBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(MeatwayBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
