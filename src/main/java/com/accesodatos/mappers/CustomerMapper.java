package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.customer.CustomerRequestDto;
import com.accesodatos.dto.customer.CustomerResponseDto;
import com.accesodatos.entity.Customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomerMapper {

	public Customer toEntity(CustomerRequestDto dto) {
		Customer customer = new Customer();

		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());

		return customer;
	}

	public CustomerResponseDto toResponseDto(Customer customer) {
		CustomerResponseDto dto = new CustomerResponseDto();

		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());

		return dto;
	}

}
