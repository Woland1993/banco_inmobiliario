package com.inmobiliario.shoppingCart.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Cart {
    private Long CardtId;   
    private Long userId;      
           
    private List<Item> items = new ArrayList<>(); 


    public Cart(Long id, Long userId) {
        this.CardtId = id;
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}