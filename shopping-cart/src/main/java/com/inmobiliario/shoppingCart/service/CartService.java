package com.inmobiliario.shoppingCart.service;

import com.inmobiliario.shoppingCart.model.Cart;
import com.inmobiliario.shoppingCart.model.Item;
import com.inmobiliario.shoppingCart.repository.CartRepository;
import com.inmobiliario.shoppingCart.validator.CartValidator;
import com.inmobiliario.shoppingCart.exceptions.*;

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
        try {
            List<Cart> carts = cartRepository.findAll();
            if (carts.isEmpty()) {
                throw new NoDataFoundException("No carts found.");
            }
            return carts;
        } catch (Exception e) {
            throw new GenerateServiceException("No carts found.");
        }
    }

    public List<Cart> getCartsByUserId(Long userId) {
        CartValidator.validateCreate(userId);
        try {
            List<Cart> carts = cartRepository.findByUserId(userId);
            if (carts.isEmpty()) {
                throw new NoDataFoundException("No carts found for user ID: " + userId);
            }
            return carts;
        } catch (Exception e) {
            throw new GenerateServiceException("No carts found for user ID: " + userId);
        }
    }

    public Optional<Cart> getCartById(Long id) {
        CartValidator.validateId(id);

        try {
            return cartRepository.findById(id)
                    .or(() -> {
                        throw new NoDataFoundException("Cart with ID " + id + " not found.");
                    });
        } catch (Exception e) {
            throw new GenerateServiceException("Cart with ID " + id + " not found.");
        }
    }

    public Cart createCart(Long userId) {
        CartValidator.validateCreate(userId);
        try {
            Cart newCart = new Cart(null, userId);
            return cartRepository.save(newCart);
        } catch (ValidateServiceException e) {
            throw e; // Re-lanzar validación
        } catch (Exception e) {
            throw new GenerateServiceException("Invalid user ID.");
        }
    }

    public Optional<Cart> addItemToCart(Long cartId, Item item) {
        CartValidator.validateItem(item);
        CartValidator.validateId(cartId);
    
        try {
            Optional<Cart> cart = cartRepository.findById(cartId);
    
            cart.ifPresentOrElse(
                c -> {
                    boolean itemExists = c.getItems().stream()
                        .anyMatch(existingItem -> existingItem.getId().equals(item.getId()));
    
                    if (!itemExists) {
                        c.getItems().add(item);
                    } else {
                        throw new GenerateServiceException("Item with ID " + item.getId() + " already exists in the cart.");
                    }
                },
                () -> { throw new NoDataFoundException("Cart with ID " + cartId + " not found."); }
            );
    
            return cart;
        } catch (Exception e) {
            throw new GenerateServiceException("Cart with ID " + cartId + " not found or Item with ID "+ item.getId() + " already exists in the cart.");
        }
    }

    public Optional<Cart> updateCart(Long cartId, List<Item> items) {
        CartValidator.validateItems(items);
        try {
            Optional<Cart> cart = cartRepository.findById(cartId);
            cart.ifPresentOrElse(
                c -> c.setItems(items),
                () -> { throw new NoDataFoundException("Cart with ID " + cartId + " not found."); }
            );
            return cart;
        } catch (Exception e) {
            throw new GenerateServiceException("Cart with ID " + cartId + " not found.");
        }
    }

    public boolean deleteCart(Long cartId) {
        CartValidator.validateId(cartId);
        try {
            boolean deleted = cartRepository.deleteById(cartId);
            if (!deleted) {
                throw new NoDataFoundException("Cart with ID " + cartId + " not found for deletion.");
            }
            return true;
        } catch (Exception e) {
            throw new GenerateServiceException("Cart with ID " + cartId + " not found for deletion.");
        }
    }

    public Optional<Cart> removeItemFromCart(Long cartId, Long itemId) {
        try{
        return cartRepository.removeItemFromCart(cartId, itemId);
        }catch(Exception e){
            throw new GenerateServiceException("Cart with ID  " + cartId + " or item ID"+itemId+" not found for deletion.");
        }
    }
}
