package com.inmobiliario.shoppingCart.controller;
import com.inmobiliario.shoppingCart.model.Cart;
import com.inmobiliario.shoppingCart.model.Item;
import com.inmobiliario.shoppingCart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
@Tag(name = "Shopping Cart", description = "API for shopping cart management")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Get all carts", description = "Retrieve a list of all shopping carts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get carts by user ID", description = "Retrieve all shopping carts for a specific user")
    public List<Cart> getCartsByUserId(@PathVariable Long userId) {
        return cartService.getCartsByUserId(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by ID", description = "Retrieve a shopping cart by its ID")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new cart", description = "Creates an empty shopping cart for a user")
    public ResponseEntity<Cart> createCart(@RequestParam Long userId) {
        Cart newCart = cartService.createCart(userId);
        return ResponseEntity.ok(newCart);
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Add an item to a cart", description = "Adds a product to an existing shopping cart")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long id, @RequestBody Item item) {
        Optional<Cart> updatedCart = cartService.addItemToCart(id, item);
        return updatedCart.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update cart items", description = "Replace the list of products in a cart")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody List<Item> items) {
        Optional<Cart> updatedCart = cartService.updateCart(id, items);
        return updatedCart.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart", description = "Removes a shopping cart by its ID")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        return cartService.deleteCart(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}