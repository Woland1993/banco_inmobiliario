package com.inmobiliario.purchaseOrders.repository;
import com.inmobiliario.purchaseOrders.model.PurchaseOrder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PurchaseOrderRepository {
    private final List<PurchaseOrder> orders = new ArrayList<>();
    private long currentId = 1;

    public PurchaseOrder save(PurchaseOrder order) {
        order.setId(currentId++);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(null);
        orders.add(order);
        return order;
    }

    public List<PurchaseOrder> findAll() {
        return orders;
    }

    public Optional<PurchaseOrder> findById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    public PurchaseOrder update(Long id, PurchaseOrder newOrder) {
        var existingOrder = findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setPaymentId(newOrder.getPaymentId());
        existingOrder.setStatus(newOrder.getStatus());
        existingOrder.setUpdatedAt(LocalDateTime.now());

        return existingOrder;
    }

    public void delete(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }

    public List<PurchaseOrder> findByCustomerId(Long customerId) {
        return orders.stream().filter(order -> order.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    public List<PurchaseOrder> findByCartId(Long cartId) {
        return orders.stream().filter(order -> order.getCartId().equals(cartId)).collect(Collectors.toList());
    }

    public List<PurchaseOrder> findByStatus(String status) {
        return orders.stream().filter(order -> order.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }
}