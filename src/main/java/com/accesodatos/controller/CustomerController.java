package com.accesodatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accesodatos.dto.customer.CustomerResponseDto;
import com.accesodatos.service.CustomerService;

import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String customerId) {
        try {
            CustomerResponseDto response = customerService.getCustomerProfile(customerId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }
}