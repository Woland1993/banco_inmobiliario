package com.inmobiliario.purchaseOrders.controller;
import com.inmobiliario.purchaseOrders.model.PurchaseOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inmobiliario.purchaseOrders.service.PurchaseOrderService;


import java.util.List;
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Purchase Orders", description = "Endpoints for managing purchase orders")
public class PurchaseOrderController {
    private final PurchaseOrderService service = new PurchaseOrderService();

    @PostMapping
    @Operation(summary = "Create a new purchase order")
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrder order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping
    @Operation(summary = "Retrieve all purchase orders")
    public ResponseEntity<List<PurchaseOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a purchase order by ID")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Retrieve orders by customer ID")
    public ResponseEntity<List<PurchaseOrder>> getOrdersByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/cart/{cartId}")
    @Operation(summary = "Retrieve orders by cart ID")
    public ResponseEntity<List<PurchaseOrder>> getOrdersByCartId(@PathVariable Long cartId) {
        return ResponseEntity.ok(service.getOrdersByCartId(cartId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Retrieve orders by status")
    public ResponseEntity<List<PurchaseOrder>> getOrdersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getOrdersByStatus(status));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a purchase order")
    public ResponseEntity<PurchaseOrder> updateOrder(@PathVariable Long id, @RequestBody PurchaseOrder order) {
        try {
            return ResponseEntity.ok(service.updateOrder(id, order));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a purchase order")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
