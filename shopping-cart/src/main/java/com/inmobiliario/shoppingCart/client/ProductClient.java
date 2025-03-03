package com.inmobiliario.shoppingCart.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inmobiliario.shoppingCart.dto.ApiResponse;
import com.inmobiliario.shoppingCart.dto.ProductDto;


@FeignClient(name = "product-service", url = "http://localhost:8081/api/products")
public interface ProductClient {
    
    @GetMapping("/{id}")
    ApiResponse<ProductDto> getProductsById(@PathVariable Long id);
}
