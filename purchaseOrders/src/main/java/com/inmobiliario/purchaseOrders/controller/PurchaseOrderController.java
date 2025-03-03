package com.inmobiliario.purchaseOrders.controller;

import com.inmobiliario.purchaseOrders.dto.ApiResponse;
import com.inmobiliario.purchaseOrders.model.PurchaseOrder;
import com.inmobiliario.purchaseOrders.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Purchase Orders", description = "Endpoints for managing purchase orders")
public class PurchaseOrderController {
    private final PurchaseOrderService service;

    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new purchase order")
    public ResponseEntity<ApiResponse<PurchaseOrder>> createOrder(@RequestBody PurchaseOrder order) {
        PurchaseOrder createdOrder = service.createOrder(order);
        return new ApiResponse<>("Order created successfully", true, createdOrder)
                .createResponse(HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve all purchase orders")
    public ResponseEntity<ApiResponse<List<PurchaseOrder>>> getAllOrders() {
        List<PurchaseOrder> orders = service.getAllOrders();
        return new ApiResponse<>("Orders retrieved successfully", true, orders)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a purchase order by ID")
    public ResponseEntity<ApiResponse<PurchaseOrder>> getOrderById(@PathVariable Long id) {
        Optional<PurchaseOrder> orderOptional = service.getOrderById(id);
    
        if (orderOptional.isPresent()) {
            return new ApiResponse<>("Order retrieved successfully", true, orderOptional.get())
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<PurchaseOrder>("Order not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Retrieve orders by customer ID")
    public ResponseEntity<ApiResponse<List<PurchaseOrder>>> getOrdersByCustomerId(@PathVariable Long customerId) {
        List<PurchaseOrder> orders = service.getOrdersByCustomerId(customerId);
        return new ApiResponse<>("Orders retrieved successfully", true, orders)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/cart/{cartId}")
    @Operation(summary = "Retrieve orders by cart ID")
    public ResponseEntity<ApiResponse<List<PurchaseOrder>>> getOrdersByCartId(@PathVariable Long cartId) {
        List<PurchaseOrder> orders = service.getOrdersByCartId(cartId);
        return new ApiResponse<>("Orders retrieved successfully", true, orders)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Retrieve orders by status")
    public ResponseEntity<ApiResponse<List<PurchaseOrder>>> getOrdersByStatus(@PathVariable String status) {
        List<PurchaseOrder> orders = service.getOrdersByStatus(status);
        return new ApiResponse<>("Orders retrieved successfully", true, orders)
                .createResponse(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a purchase order")
    public ResponseEntity<ApiResponse<PurchaseOrder>> updateOrder(@PathVariable Long id, @RequestBody PurchaseOrder order) {
        Optional<PurchaseOrder> orderOptional = service.getOrderById(id);
    
        if (orderOptional.isPresent()) {
            PurchaseOrder updatedOrder = service.updateOrder(id, order);
            return new ApiResponse<>("Order updated successfully", true, updatedOrder)
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<PurchaseOrder>("Order not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a purchase order")
    public ResponseEntity<ApiResponse<Object>> deleteOrder(@PathVariable Long id) {
        Optional<PurchaseOrder> orderOptional = service.getOrderById(id);
    
        if (orderOptional.isPresent()) {
            service.deleteOrder(id);
            return new ApiResponse<>("Order deleted successfully", true, null)
                    .createResponse(HttpStatus.NO_CONTENT);
        } else {
            return new ApiResponse<>("Order not found", false, null)
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }
    
}
