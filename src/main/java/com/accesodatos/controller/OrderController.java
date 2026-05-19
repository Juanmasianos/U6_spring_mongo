package com.accesodatos.controller;

import com.accesodatos.dto.order.OrderRequestDto;
import com.accesodatos.dto.order.OrderResponseDto;
import com.accesodatos.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/v1/checkout")
    public ResponseEntity<?> checkoutV1(@RequestParam String customerId, @Valid @RequestBody OrderRequestDto request) {
        try {
            OrderResponseDto response = orderService.checkoutOne(customerId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/v2/checkout")
    public ResponseEntity<?> checkoutV2(@RequestParam String customerId, @Valid @RequestBody OrderRequestDto request) {
        try {
            OrderResponseDto response = orderService.checkoutTwo(customerId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<List<OrderResponseDto>> getCustomerOrders(@RequestParam String customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }
}