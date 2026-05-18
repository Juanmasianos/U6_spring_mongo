package com.accesodatos.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private String orderId;
    private String customerId;
    private List<OrderItemResponseDto> items;
    private Double totalAmount;
    private String paymentMethod;
    private LocalDateTime createdAt;
}
