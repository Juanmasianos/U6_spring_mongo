package com.accesodatos.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginResponse {

    private String username;
    private Set<String> roles;
    private String accessToken;




}
