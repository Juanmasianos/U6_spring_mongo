package com.accesodatos.service.impl;

import com.accesodatos.dto.auth.AuthLoginRequest;
import com.accesodatos.dto.auth.AuthLoginResponse;
import com.accesodatos.dto.customer.CustomerResponseDto;
import com.accesodatos.entity.Customer;
import com.accesodatos.jwt.JwtTokenProvider;
import com.accesodatos.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomerRepository customerRepository;

    public AuthLoginResponse login(AuthLoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        Customer customer = customerRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(authentication);
        Set<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> auth.startsWith("ROLE_"))
                .collect(Collectors.toSet());


        return new AuthLoginResponse(customer.getId(), customer.getName(), customer.getEmail(), roles, accessToken);

    }
}
