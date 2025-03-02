package com.inmobiliario.shoppingCart.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;       
    private String name;   
    private double price;  
    private int quantity;  
}
