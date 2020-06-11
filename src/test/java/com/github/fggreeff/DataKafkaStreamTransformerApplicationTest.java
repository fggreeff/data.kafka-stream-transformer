package com.github.fggreeff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-local.yml")
@ContextConfiguration(
        initializers = {ConfigFileApplicationContextInitializer.class}
)
class DataKafkaStreamTransformerApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        assertNotNull(context);
    }
}
