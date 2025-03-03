package com.inmobiliario.purchaseOrders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.inmobiliario.purchaseOrders.dto.*;


@FeignClient(name = "orders-service", url = "http://localhost:8082/api/carts")
public interface CartClient {
    
    @GetMapping("/{id}")
    ApiResponse<Cart> getCartById(@PathVariable Long id);
}
