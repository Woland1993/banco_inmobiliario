package com.inmobiliario.products_service.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.inmobiliario.products_service.dto.ProductDto;

import java.util.List;

@FeignClient(name = "fakeStoreClient", url = "https://fakestoreapi.com")

public interface  FakeStoreClient {
    
    @GetMapping("/products")
    List<ProductDto> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable Long id);
}
