package com.accesodatos.service;

import com.accesodatos.dto.customer.CustomerRequestDto;
import com.accesodatos.dto.customer.CustomerResponseDto;
import com.accesodatos.entity.Customer;

public interface CustomerService {
    CustomerResponseDto registerCustomer(CustomerRequestDto request);
    CustomerResponseDto getCustomerProfile(String customerId);
}