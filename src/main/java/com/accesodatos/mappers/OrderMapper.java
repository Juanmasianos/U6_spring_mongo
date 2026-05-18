package com.accesodatos.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.order.OrderItemResponseDto;
import com.accesodatos.dto.order.OrderResponseDto;
import com.accesodatos.entity.Order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrderMapper {

    public OrderResponseDto mapToOrderResponse(Order order) {
        List<OrderItemResponseDto> itemResponses = order.getItems().stream()
                .map(item -> new OrderItemResponseDto(item.getProductId(), item.getName(), item.getPrice(),
                        item.getQuantity()))
                .collect(Collectors.toList());

        return new OrderResponseDto(
                order.getId(),
                order.getCustomerId(),
                itemResponses,
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCreatedAt());
    }

}