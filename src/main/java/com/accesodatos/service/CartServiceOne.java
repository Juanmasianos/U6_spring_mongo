package com.accesodatos.service;


import com.accesodatos.entity.CartVersionOne;

public interface CartServiceOne {
	
    public CartVersionOne getOrCreateCart(String customerId);

    public CartVersionOne addProduct(String customerId, String productId, Integer quantity);

    public CartVersionOne updateQuantity(String customerId, String productId, Integer quantity);

    public CartVersionOne removeProduct(String customerId, String productId);

    public CartVersionOne clearCart(String customerId);

}
