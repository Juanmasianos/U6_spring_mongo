package com.accesodatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accesodatos.entity.CartVersionTwo;
import com.accesodatos.service.CartServiceTwo;

@RestController
@RequestMapping("/v2/cart")
public class CartVersionTwoController {

    @Autowired private CartServiceTwo cartService;

    @GetMapping
    public ResponseEntity<CartVersionTwo> getCart(@RequestParam String customerId) {
        return ResponseEntity.ok(cartService.getOrCreateCart(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartVersionTwo> addProduct(@RequestParam String customerId, @RequestParam String productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.addProduct(customerId, productId, quantity));
    }

    @PutMapping("/update")
    public ResponseEntity<CartVersionTwo> updateQuantity(@RequestParam String customerId, @RequestParam String productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(customerId, productId, quantity));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartVersionTwo> removeProduct(@RequestParam String customerId, @RequestParam String productId) {
        return ResponseEntity.ok(cartService.removeProduct(customerId, productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartVersionTwo> clearCart(@RequestParam String customerId) {
        return ResponseEntity.ok(cartService.clearCart(customerId));
    }
}