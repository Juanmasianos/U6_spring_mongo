package com.accesodatos.service;

import com.accesodatos.entity.CartVersionTwo;

public interface CartServiceTwo {

  public CartVersionTwo getOrCreateCart(String customerId);

  public CartVersionTwo addProduct(String customerId, String productId, Integer quantity);

  public CartVersionTwo updateQuantity(String customerId, String productId, Integer quantity);

  public CartVersionTwo removeProduct(String customerId, String productId);

  public CartVersionTwo clearCart(String customerId);
}