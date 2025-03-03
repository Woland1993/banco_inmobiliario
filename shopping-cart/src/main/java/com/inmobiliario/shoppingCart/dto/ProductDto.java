package com.inmobiliario.shoppingCart.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
    private RatingDto rating; // Nuevo campo agregado
}