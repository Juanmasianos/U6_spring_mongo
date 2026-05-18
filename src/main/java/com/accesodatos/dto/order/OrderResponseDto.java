package com.accesodatos.dto.order;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private String orderId;
    private String customerId;
    private List<OrderItemResponseDto> items;
    private Double totalAmount;
    private String paymentMethod;
    private LocalDateTime createdAt;
}
