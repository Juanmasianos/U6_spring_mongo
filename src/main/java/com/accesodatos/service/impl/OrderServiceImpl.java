package com.accesodatos.service.impl;

import com.accesodatos.dto.order.OrderRequestDto;
import com.accesodatos.dto.order.OrderResponseDto;
import com.accesodatos.entity.CartVersionOne;
import com.accesodatos.entity.CartVersionTwo;
import com.accesodatos.entity.Order;
import com.accesodatos.entity.OrderItem;
import com.accesodatos.entity.Product;
import com.accesodatos.mappers.OrderMapper;
import com.accesodatos.repository.CartRepositoryOne;
import com.accesodatos.repository.CartRepositoryTwo;
import com.accesodatos.repository.OrderRepository;
import com.accesodatos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accesodatos.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired 
    private OrderRepository orderRepository;
    @Autowired 
    private ProductRepository productRepository;
    @Autowired 
    private CartRepositoryOne cartRepositoryOne;
    @Autowired 
    private CartRepositoryTwo cartRepositoryTwo;
    @Autowired
    private OrderMapper orderMapper;

    
    public List<OrderResponseDto> getOrdersByCustomer(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(orderMapper::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    
    public OrderResponseDto checkoutOne(String customerId, OrderRequestDto request) {
        CartVersionOne cart = cartRepositoryOne.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Carrito v1 no encontrado"));

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        Double totalAmount = 0.0;

        
        for (var cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + cartItem.getName()));

            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + product.getName());
            }
            
            totalAmount += cartItem.getPrice() * cartItem.getQuantity();
            orderItems.add(new OrderItem(product.getId(), product.getName(), product.getPrice(), cartItem.getQuantity()));
        }

        
        for (var cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId()).get();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }

        
        Order order = new Order().builder()
                .customerId(customerId)
                .items(orderItems)
                .totalAmount(totalAmount)
                .paymentMethod(request.getPaymentMethod())
                .build();
        Order savedOrder = orderRepository.save(order);

        
        cart.getCartItems().clear();
        cartRepositoryOne.save(cart);

        return orderMapper.mapToOrderResponse(savedOrder);
    }

    
    public OrderResponseDto checkoutTwo(String customerId, OrderRequestDto request) {
        CartVersionTwo cart = cartRepositoryTwo.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Carrito v2 no encontrado"));

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        Double totalAmount = 0.0;

        for (var cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("El producto ya no existe en la tienda"));

            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + product.getName());
            }

            totalAmount += product.getPrice() * cartItem.getQuantity();
            orderItems.add(new OrderItem(product.getId(), product.getName(), product.getPrice(), cartItem.getQuantity()));
        }

        for (var cartItem : cart.getCartItems()) {
            Product product = productRepository.findById(cartItem.getProductId()).get();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }
        Order order = new Order().builder()
                .customerId(customerId)
                .items(orderItems)
                .totalAmount(totalAmount)
                .paymentMethod(request.getPaymentMethod())
                .build();
        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepositoryTwo.save(cart);

        return orderMapper.mapToOrderResponse(savedOrder);
    }


}