package com.accesodatos.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "carts_v2")
public class CartVersionTwo {
    @Id
    private String id;
    private String customerId;
    private List<CartItemVersionTwo> cartItems = new ArrayList<>();
}