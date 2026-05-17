package com.accesodatos.controller;

import com.accesodatos.dto.customer.CustomerRequestDto;
import com.accesodatos.entity.Customer;
import com.example.shop.service.common.CustomerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. REGISTRO DE USUARIO
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (customerService.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().body(Map.of("message", "El email ya existe"));
        }

        Customer newCustomer = new Customer(request.name(), request.email(), request.password());
        customerService.save(newCustomer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuario registrado con éxito"));
    }

    // 2. LOGIN DE USUARIO (Simulado sin JWT por ahora, validando que coincidan datos)
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Customer customer = customerService.findByEmail(request.email())
                .orElse(null);

        if (customer == null || !customer.getPassword().equals(request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Credenciales incorrectas"));
        }

        return ResponseEntity.ok(Map.of("message", "Login correcto (Simulado)", "customerId", customer.getId()));
    }

    // 3. OBTENER PERFIL DEL USUARIO
    // Por ahora le pasamos el id por parámetro para simular la consulta del perfil
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String customerId) {
        Customer customer = customerService.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ProfileResponse response = new ProfileResponse(customer.getId(), customer.getName(), customer.getEmail());
        return ResponseEntity.ok(response);
    }
}