package ru.gerasimov.home_budget;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public class TestContainerConfig {
    public static class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
                DockerImageName.parse("postgres:15.0")).withInitScript("schema.sql");

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            Startables.deepStart(postgres).join();

            TestPropertyValues.of("spring.datasource.url=" + postgres.getJdbcUrl(),
                            "spring.datasource.username=" + postgres.getUsername(),
                            "spring.datasource.password=" + postgres.getPassword())
                    .applyTo(applicationContext.getEnvironment());
        }
    }
}
