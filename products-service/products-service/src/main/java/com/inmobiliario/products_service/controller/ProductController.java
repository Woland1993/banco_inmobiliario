package com.inmobiliario.products_service.controller;

import com.inmobiliario.products_service.dto.ApiResponse;
import com.inmobiliario.products_service.dto.ProductDto;
import com.inmobiliario.products_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "Endpoints for managing products")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Returns a list of all products from FakeStore API")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        return new ApiResponse<>("Products retrieved successfully", true, productService.getAllProducts())
                .createResponse(HttpStatus.OK);
    }

    @Operation(summary = "Get product by ID", description = "Returns a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>>  getProductById(@PathVariable Long id) {
        return new ApiResponse<>("Product retrieved successfully", true, productService.getProductById(id))
        .createResponse(HttpStatus.OK);
    }
}