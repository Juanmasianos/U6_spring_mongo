package com.accesodatos.service;

import com.accesodatos.dto.customer.CustomerRequestDto;
import com.accesodatos.dto.customer.CustomerResponseDto;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRequestDto request);
    CustomerResponseDto getCustomerProfile(String customerId);
}