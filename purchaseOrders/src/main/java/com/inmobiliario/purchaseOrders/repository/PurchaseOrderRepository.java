package com.inmobiliario.purchaseOrders.repository;

import com.inmobiliario.purchaseOrders.exceptions.*;
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
        try {
            boolean exists = orders.stream()
                    .anyMatch(existingOrder -> existingOrder.getCartId().equals(order.getCartId()));
            
            if (exists) {
                throw new GenerateServiceException("A purchase order with the same cartId already exists");
            }
            order.setStatus("PENDING");
            order.setId(currentId++);
            order.setCreatedAt(LocalDateTime.now());
            order.setUpdatedAt(null);
            orders.add(order);
            return order;
        } catch (Exception e) {
            throw new GenerateServiceException("A purchase order with the same cartId already exist");
        }
    }
    
    public List<PurchaseOrder> findAll() {
        try {
            if (orders.isEmpty()) {
                throw new NoDataFoundException("No purchase orders found.");
            }
            return orders;
        } catch (Exception e) {
            throw new GenerateServiceException("Error retrieving purchase order list");
        }
    }

    public Optional<PurchaseOrder> findById(Long id) {
        try {
            return orders.stream()
                    .filter(order -> order.getId().equals(id))
                    .findFirst()
                    .or(() -> {
                        throw new NoDataFoundException("Purchase order with ID: " + id + " not found.");
                    });
        } catch (Exception e) {
            throw new GenerateServiceException("Error searching for purchase order");
        }
    }

    public PurchaseOrder update(Long id, PurchaseOrder newOrder) {
        try {
            var existingOrder = findById(id).orElseThrow(() -> 
                new NoDataFoundException("Purchase order with ID: " + id + " not found for update.")
            );
            existingOrder.setPaymentId(newOrder.getPaymentId());
            existingOrder.setStatus(newOrder.getStatus());
            existingOrder.setUpdatedAt(LocalDateTime.now());

            return existingOrder;
        } catch (NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GenerateServiceException("Error updating purchase order");
        }
    }

    public void delete(Long id) {
        try {
            boolean removed = orders.removeIf(order -> order.getId().equals(id));
            if (!removed) {
                throw new NoDataFoundException("Purchase order with ID: " + id + " not found for deletion.");
            }
        } catch (Exception e) {
            throw new GenerateServiceException("Error deleting purchase order");
        }
    }

    public List<PurchaseOrder> findByCustomerId(Long customerId) {
        try {
            List<PurchaseOrder> result = orders.stream()
                    .filter(order -> order.getCustomerId().equals(customerId))
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new NoDataFoundException("No purchase orders found for customer ID: " + customerId);
            }
            return result;
        } catch (Exception e) {
            throw new GenerateServiceException("Error retrieving purchase orders by customer ID");
        }
    }

    public List<PurchaseOrder> findByCartId(Long cartId) {
        try {
            List<PurchaseOrder> result = orders.stream()
                    .filter(order -> order.getCartId().equals(cartId))
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new NoDataFoundException("No purchase orders found for cart ID: " + cartId);
            }
            return result;
        } catch (Exception e) {
            throw new GenerateServiceException("Error retrieving purchase orders by cart ID");
        }
    }

    public List<PurchaseOrder> findByStatus(String status) {
        try {
            List<PurchaseOrder> result = orders.stream()
                    .filter(order -> order.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new NoDataFoundException("No purchase orders found with status: " + status);
            }
            return result;
        } catch (Exception e) {
            throw new GenerateServiceException("Error retrieving purchase orders by status");
        }
    }
}
