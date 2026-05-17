package com.accesodatos.service;

import com.accesodatos.dto.auth.AuthRegisterRequest;
import com.accesodatos.dto.auth.AuthRegisterResponse;

public interface UserService {

    public AuthRegisterResponse register(AuthRegisterRequest authRegisterRequest);
}
