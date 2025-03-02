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

    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Cart createCart() {
        return cartRepository.save(new Cart());
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
