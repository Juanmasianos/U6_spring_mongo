package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.order.OrderRequestDto;
import com.accesodatos.dto.order.OrderResponseDto;

public interface OrderService {

    public List<OrderResponseDto> getOrdersByCustomer(String customerId);
    
    public OrderResponseDto checkoutV1(String customerId, OrderRequestDto request);
    
    public OrderResponseDto checkoutV2(String customerId, OrderRequestDto request);
}
