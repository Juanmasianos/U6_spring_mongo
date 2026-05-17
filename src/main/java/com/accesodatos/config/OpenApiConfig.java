package com.accesodatos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
    info = @Info(
        title = "JPARepository",
        description = "Our app provides a CRUD of courses, students and instructors",
        termsOfService = "www.accesodatos.com/terminos_y_condiciones",
        version = "1.0.0",
        contact = @Contact(
            name = "Juan Manuel",
            url = "https://salesianos-lacuesta.com",
            email = "usr3507@salesianos-lacuesta.net"
        ),
        license = @License(
            name = "Standard Software use License for Acceso a Datos",
            url = "https://lacuesta.salesianos.edu/license"
        )
    ),
    servers = {
            @Server(
                    description = "Server URL in Development env",
                    url = "http://localhost:8081"
            ),
            @Server(
                    description = "Server URL in Production env",
                    url = "http://localhost:8081"
            )
    },
    security = @SecurityRequirement(
            name = "Token_JWT"
    )
)
@SecurityScheme(
        name = "Token_JWT",
        description = "Pega aquí tu token JWT",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {



}
