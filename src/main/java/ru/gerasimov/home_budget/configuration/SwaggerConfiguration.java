package ru.gerasimov.home_budget.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gerasimov.home_budget.configuration.properties.OpenApiProperties;

/**
 * Конфигурация для Open API.
 *
 * @author Evgeniy Gerasimov.
 */
@Configuration
@EnableConfigurationProperties(OpenApiProperties.class)
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenApi(OpenApiProperties openApiProperties) {
        return openApiProperties.getProperties();
    }
}
