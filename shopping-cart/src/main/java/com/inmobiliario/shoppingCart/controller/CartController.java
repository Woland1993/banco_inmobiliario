package com.inmobiliario.shoppingCart.controller;
import com.inmobiliario.shoppingCart.model.Cart;
import com.inmobiliario.shoppingCart.model.Item;
import com.inmobiliario.shoppingCart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliario.shoppingCart.dto.*;

import org.springframework.http.HttpStatus;


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
    public ResponseEntity<ApiResponse<List<Cart>>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ApiResponse<>("Carts retrieved successfully", true, carts)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get carts by user ID", description = "Retrieve all shopping carts for a specific user")
    public ResponseEntity<ApiResponse<List<Cart>>> getCartsByUserId(@PathVariable Long userId) {
        List<Cart> carts = cartService.getCartsByUserId(userId);
        return new ApiResponse<>("Carts retrieved successfully", true, carts)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a cart by ID", description = "Retrieve a shopping cart by its ID")
    public ResponseEntity<ApiResponse<Cart>> getCartById(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.getCartById(id);
    
        if (cartOptional.isPresent()) {
            return new ApiResponse<>("Cart retrieved successfully", true, cartOptional.get())
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<Cart>("Cart not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new cart", description = "Creates an empty shopping cart for a user")
    public ResponseEntity<ApiResponse<Cart>> createCart(@RequestParam Long userId) {
        Cart newCart = cartService.createCart(userId);
        return new ApiResponse<>("Cart created successfully", true, newCart)
                .createResponse(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Add an item to a cart", description = "Adds a product to an existing shopping cart")
    public ResponseEntity<ApiResponse<Cart>> addItemToCart(@PathVariable Long id, @RequestBody Item item) {
        Optional<Cart> updatedCart = cartService.addItemToCart(id, item);
    
        if (updatedCart.isPresent()) {
            return new ApiResponse<>("Item added successfully", true, updatedCart.get())
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<Cart>("Cart not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update cart items", description = "Replace the list of products in a cart")
    public ResponseEntity<ApiResponse<Cart>> updateCart(@PathVariable Long id, @RequestBody List<Item> items) {
        Optional<Cart> updatedCart = cartService.updateCart(id, items);
    
        if (updatedCart.isPresent()) {
            return new ApiResponse<>("Cart updated successfully", true, updatedCart.get())
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<Cart>("Cart not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a cart", description = "Removes a shopping cart by its ID")
    public ResponseEntity<ApiResponse<Object>> deleteCart(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.getCartById(id);

        if (cartOptional.isPresent()) {
            cartService.deleteCart(id);
            return new ApiResponse<>("Cart deleted successfully", true, null)
                    .createResponse(HttpStatus.NO_CONTENT);
        } else {
            return new ApiResponse<>("Cart not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

        @DeleteMapping("/{cartId}/items/{itemId}")
        @Operation(summary = "Remove an item from a cart", description = "Deletes a specific item from the cart")
        public ResponseEntity<ApiResponse<Cart>> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
            Optional<Cart> updatedCart = cartService.removeItemFromCart(cartId, itemId);

            if (updatedCart.isPresent()) {
                return new ApiResponse<>("Item removed successfully", true, updatedCart.get())
                        .createResponse(HttpStatus.OK);
            } else {
                return new ApiResponse<Cart>("Cart or item not found", false, null)
                        .createResponse(HttpStatus.NOT_FOUND);
            }
        }

}
