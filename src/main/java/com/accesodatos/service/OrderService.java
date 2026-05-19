package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.order.OrderRequestDto;
import com.accesodatos.dto.order.OrderResponseDto;

public interface OrderService {

    public List<OrderResponseDto> getOrdersByCustomer(String customerId);
    
    public OrderResponseDto checkoutOne(String customerId, OrderRequestDto request);
    
    public OrderResponseDto checkoutTwo(String customerId, OrderRequestDto request);
}
