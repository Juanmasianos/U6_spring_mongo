package com.accesodatos.service;

import com.accesodatos.dto.auth.AuthRegisterResponse;
import com.accesodatos.dto.customer.CustomerRequestDto;

public interface UserService {

    public AuthRegisterResponse register(CustomerRequestDto authRegisterRequest);
}
