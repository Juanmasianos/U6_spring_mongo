package com.accesodatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accesodatos.entity.CartVersionOne;
import com.accesodatos.service.CartServiceOne;

@RestController
@RequestMapping("/v1/cart")
public class CartVersionOneController {

    @Autowired private CartServiceOne cartService;

    @GetMapping
    public ResponseEntity<CartVersionOne> getCart(@RequestParam String customerId) {
        return ResponseEntity.ok(cartService.getOrCreateCart(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartVersionOne> addProduct(@RequestParam String customerId, @RequestParam String productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.addProduct(customerId, productId, quantity));
    }

    @PutMapping("/update")
    public ResponseEntity<CartVersionOne> updateQuantity(@RequestParam String customerId, @RequestParam String productId, @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(customerId, productId, quantity));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartVersionOne> removeProduct(@RequestParam String customerId, @RequestParam String productId) {
        return ResponseEntity.ok(cartService.removeProduct(customerId, productId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<CartVersionOne> clearCart(@RequestParam String customerId) {
        return ResponseEntity.ok(cartService.clearCart(customerId));
    }
}