package com.accesodatos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(security = @SecurityRequirement(name = "Token_JWT"))
@SecurityScheme(name = "Token_JWT", description = "Pega aquí tu token JWT", type = SecuritySchemeType.HTTP, paramName = HttpHeaders.AUTHORIZATION, in = SecuritySchemeIn.HEADER, scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {

}