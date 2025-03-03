package com.inmobiliario.shoppingCart.repository;

import com.inmobiliario.shoppingCart.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CartRepository {

    private final List<Cart> carts = new ArrayList<>();
    private Long nextCartId = 1L;

    public List<Cart> findAll() {
        return carts;
    }

    public Optional<Cart> findById(Long id) {
        return carts.stream().filter(cart -> cart.getCardtId().equals(id)).findFirst();
    }

    public List<Cart> findByUserId(Long userId) {
        return carts.stream().filter(cart -> cart.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public Cart save(Cart cart) {
        if (cart.getCardtId() == null) {
            cart.setCardtId(nextCartId++);
        }
        carts.add(cart);
        return cart;
    }

    public boolean deleteById(Long id) {
        return carts.removeIf(cart -> cart.getCardtId().equals(id));
    }

    public Optional<Cart> removeItemFromCart(Long cartId, Long itemId) {
        Optional<Cart> cart = findById(cartId);
        cart.ifPresent(c -> c.getItems().removeIf(item -> item.getId().equals(itemId)));
        return cart;
    }

    
}