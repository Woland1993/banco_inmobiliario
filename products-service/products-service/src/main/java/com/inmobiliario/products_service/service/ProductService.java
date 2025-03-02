package com.inmobiliario.products_service.service;
import com.inmobiliario.products_service.client.FakeStoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inmobiliario.products_service.dto.ProductDto;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FakeStoreClient fakeStoreClient;

    public List<ProductDto> getAllProducts() {
        return fakeStoreClient.getAllProducts();
    }

    public ProductDto getProductById(Long id) {
        return fakeStoreClient.getProductById(id);
    }
}