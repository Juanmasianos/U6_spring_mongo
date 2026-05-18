package com.accesodatos.service.impl;

import com.accesodatos.dto.customer.CustomerRequestDto;
import com.accesodatos.dto.customer.CustomerResponseDto;
import com.accesodatos.entity.Customer;
import com.accesodatos.repository.CustomerRepository;
import com.accesodatos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto registerCustomer(CustomerRequestDto request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Customer newCustomer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        customerRepository.save(newCustomer);
        return new CustomerResponseDto(newCustomer.getId(), newCustomer.getName(), newCustomer.getEmail());
    }


    @Override
    public CustomerResponseDto getCustomerProfile(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new CustomerResponseDto(customer.getId(), customer.getName(), customer.getEmail());
    }
}