package com.inmobiliario.shoppingCart.service;
import com.inmobiliario.shoppingCart.model.Cart;
import com.inmobiliario.shoppingCart.model.Item;
import com.inmobiliario.shoppingCart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart createCart(Long userId) {
        Cart newCart = new Cart(null, userId);
        return cartRepository.save(newCart);
    }

    public Optional<Cart> addItemToCart(Long cartId, Item item) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        cart.ifPresent(c -> c.getItems().add(item));
        return cart;
    }

    public Optional<Cart> updateCart(Long cartId, List<Item> items) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        cart.ifPresent(c -> c.setItems(items));
        return cart;
    }

    public boolean deleteCart(Long cartId) {
        return cartRepository.deleteById(cartId);
    }
}