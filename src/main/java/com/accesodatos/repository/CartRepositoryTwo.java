package com.accesodatos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.accesodatos.entity.CartVersionTwo;

import java.util.Optional;

public interface CartRepositoryTwo extends MongoRepository<CartVersionTwo, String> {
    Optional<CartVersionTwo> findByCustomerId(String customerId);
}