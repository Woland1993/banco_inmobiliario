package com.inmobiliario.shoppingCart.validator;
import com.inmobiliario.shoppingCart.exceptions.ValidateServiceException;
import com.inmobiliario.shoppingCart.model.Item;
import java.util.List;

public class CartValidator {

    public static void validateCreate(Long userId) {
        if (userId == null || userId <= 0) {
            throw new ValidateServiceException("User ID is required and must be greater than zero.");
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidateServiceException("Cart ID is required and must be greater than zero.");
        }
    }

    public static void validateItemId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidateServiceException("Item  ID is required and must be greater than zero.");
        }
    }

    public static void validateItem(Item item) {
        if (item == null) {
            throw new ValidateServiceException("Item cannot be null.");
        }
        if (item.getId() == null || item.getId() <= 0) {
            throw new ValidateServiceException("Item ID is required and must be greater than zero.");
        }
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new ValidateServiceException("Item name is required.");
        }
        if (item.getPrice() <= 0) {
            throw new ValidateServiceException("Item price must be greater than zero.");
        }
        if (item.getQuantity() <= 0) {
            throw new ValidateServiceException("Item quantity must be greater than zero.");
        }
    }

    public static void validateItems(List<Item> items){
        if (items == null || items.isEmpty()) {
            throw new ValidateServiceException("Cart items cannot be empty.");
        }
        for(Item i:items){
          validateItem(i);
        }
      }


    public static void validateUpdate(Long id, List<Item> items) {
        validateId(id);
        if (items == null || items.isEmpty()) {
            throw new ValidateServiceException("Cart items cannot be empty.");
        }
        for (Item item : items) {
            validateItem(item);
        }
    }
}
