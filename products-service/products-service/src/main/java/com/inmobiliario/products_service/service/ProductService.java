package com.inmobiliario.products_service.service;
import com.inmobiliario.products_service.client.FakeStoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.inmobiliario.products_service.dto.ProductDto;
import com.inmobiliario.products_service.exceptions.ClientErrorException;
import com.inmobiliario.products_service.exceptions.NoDataFoundException;
import com.inmobiliario.products_service.exceptions.ServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final FakeStoreClient fakeStoreClient;

    public List<ProductDto> getAllProducts() {
        try {
            List<ProductDto> products = fakeStoreClient.getAllProducts();
            if (products.isEmpty()) {
                throw new NoDataFoundException("No products found");
            }
            return products;
        } catch (Exception e) {
            throw new ServerErrorException("Error retrieving products");
        }
    }

    public ProductDto getProductById(Long id) {
        try {
            ProductDto product = fakeStoreClient.getProductById(id);
            if (product == null) {
                throw new NoDataFoundException("Product not found with ID: " + id);
            }
            return product;
        } catch (ClientErrorException e) {
            throw new ClientErrorException("Invalid product ID: " + id);
        } catch (Exception e) {
            throw new ServerErrorException("Error retrieving product with ID: " + id);
        }
    }
}
