package com.accesodatos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.CartItemVersionTwo;
import com.accesodatos.entity.CartVersionTwo;
import com.accesodatos.repository.CartRepositoryTwo;
import com.accesodatos.repository.ProductRepository;
import com.accesodatos.service.CartServiceTwo;

@Service
public class CartServiceTwoImpl implements CartServiceTwo {

    @Autowired 
    private CartRepositoryTwo cartRepository;
    @Autowired 
    private ProductRepository productRepository; 

    public CartVersionTwo getOrCreateCart(String customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    CartVersionTwo newCart = new CartVersionTwo();
                    newCart.setCustomerId(customerId);
                    return cartRepository.save(newCart);
                });
    }

    public CartVersionTwo addProduct(String customerId, String productId, Integer quantity) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Producto no encontrado");
        }

        CartVersionTwo cart = getOrCreateCart(customerId);

        for (CartItemVersionTwo item : cart.getCartItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(cart);
            }
        }

        cart.getCartItems().add(new CartItemVersionTwo(productId, quantity));
        return cartRepository.save(cart);
    }

    public CartVersionTwo updateQuantity(String customerId, String productId, Integer quantity) {
        CartVersionTwo cart = getOrCreateCart(customerId);
        cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
        return cartRepository.save(cart);
    }

    public CartVersionTwo removeProduct(String customerId, String productId) {
        CartVersionTwo cart = getOrCreateCart(customerId);
        cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    public CartVersionTwo clearCart(String customerId) {
        CartVersionTwo cart = getOrCreateCart(customerId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}