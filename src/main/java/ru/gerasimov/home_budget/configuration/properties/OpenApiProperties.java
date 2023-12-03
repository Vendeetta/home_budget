package ru.gerasimov.home_budget.configuration.properties;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Настройки для Open API.
 *
 * @author Evgeniy Gerasimov.
 */
@Data
@ConfigurationProperties("api")
public class OpenApiProperties {
    private OpenAPI properties;
}
