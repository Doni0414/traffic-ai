package com.doni.trafficAI.trafficService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Traffic Controller REST API",
                version = "1.0"
        )
)
@SecurityScheme(
        name = "keycloak",
        type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/.well-known/openid-configuration"
)
public class OpenAPIConfig {
}
