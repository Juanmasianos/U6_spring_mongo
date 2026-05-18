package com.accesodatos.repository;

import com.accesodatos.entity.CartVersionOne;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CartRepositoryOne extends MongoRepository<CartVersionOne, String> {
    Optional<CartVersionOne> findByCustomerId(String customerId);
}