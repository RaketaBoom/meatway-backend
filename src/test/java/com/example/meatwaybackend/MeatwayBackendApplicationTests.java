package com.example.meatwaybackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MeatwayBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
