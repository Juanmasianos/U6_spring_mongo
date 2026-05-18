package com.accesodatos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.accesodatos.entity.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
}