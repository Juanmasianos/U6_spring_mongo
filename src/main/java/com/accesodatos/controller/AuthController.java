/*package com.accesodatos.controller;

import com.accesodatos.dto.auth.AuthLoginRequest;
import com.accesodatos.dto.auth.AuthLoginResponse;
import com.accesodatos.dto.auth.AuthRegisterRequest;
import com.accesodatos.dto.auth.AuthRegisterResponse;
import com.accesodatos.service.impl.AuthService;
import com.accesodatos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Authentication",
        description = "Endpoints para la gestión de autenticación y registro de usuarios"
)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Inicio de sesión",
            description = "Autentica a un usuario y devuelve un token jwt y la info del usuario."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest loginRequest) {

        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);

    }

    @Operation(
            summary = "Registro de usuario",
            description = "Crea una nueva cuenta de usuario en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
    })
    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> login(@RequestBody AuthRegisterRequest registerRequest) {

        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED);

    }

}
*/