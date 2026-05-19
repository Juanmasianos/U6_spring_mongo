package com.accesodatos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.CartItemVersionOne;
import com.accesodatos.entity.CartVersionOne;
import com.accesodatos.entity.Product;
import com.accesodatos.repository.CartRepositoryOne;
import com.accesodatos.repository.ProductRepository;
import com.accesodatos.service.CartServiceOne;

@Service
public class CartServiceOneImpl implements CartServiceOne {

    @Autowired 
    private CartRepositoryOne cartRepository;
    @Autowired 
    private ProductRepository productRepository;

    public CartVersionOne getOrCreateCart(String customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    CartVersionOne newCart = new CartVersionOne();
                    newCart.setCustomerId(customerId);
                    return cartRepository.save(newCart);
                });
    }

    public CartVersionOne addProduct(String customerId, String productId, Integer quantity) {
        CartVersionOne cart = getOrCreateCart(customerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        for (CartItemVersionOne item : cart.getCartItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(cart);
            }
        }

        cart.getCartItems().add(new CartItemVersionOne(productId, product.getName(), product.getPrice(), quantity));
        return cartRepository.save(cart);
    }

    public CartVersionOne updateQuantity(String customerId, String productId, Integer quantity) {
        CartVersionOne cart = getOrCreateCart(customerId);
        cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
        return cartRepository.save(cart);
    }

    public CartVersionOne removeProduct(String customerId, String productId) {
        CartVersionOne cart = getOrCreateCart(customerId);
        cart.getCartItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    public CartVersionOne clearCart(String customerId) {
        CartVersionOne cart = getOrCreateCart(customerId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}