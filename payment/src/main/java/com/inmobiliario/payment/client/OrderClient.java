package com.inmobiliario.payment.client;
import  org.springframework.cloud.openfeign.FeignClient;
import  org.springframework.web.bind.annotation.*;

import com.inmobiliario.payment.dto.OrderUpdateRequest;

@FeignClient(name = "orders-service", url = "http://localhost:8083/api/orders")
public interface OrderClient {

    @PutMapping("/{id}")
    void updateOrder(@PathVariable Long id, @RequestBody OrderUpdateRequest request);
}