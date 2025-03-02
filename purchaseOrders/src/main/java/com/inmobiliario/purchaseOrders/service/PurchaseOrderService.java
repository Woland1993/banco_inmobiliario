package com.inmobiliario.purchaseOrders.service;

import com.inmobiliario.purchaseOrders.model.PurchaseOrder;
import com.inmobiliario.purchaseOrders.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository repository = new PurchaseOrderRepository();

    public PurchaseOrder createOrder(PurchaseOrder order) {
        return repository.save(order);
    }

    public List<PurchaseOrder> getAllOrders() {
        return repository.findAll();
    }

    public Optional<PurchaseOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    public List<PurchaseOrder> getOrdersByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<PurchaseOrder> getOrdersByCartId(Long cartId) {
        return repository.findByCartId(cartId);
    }

    public List<PurchaseOrder> getOrdersByStatus(String status) {
        return repository.findByStatus(status);
    }

    public PurchaseOrder updateOrder(Long id, PurchaseOrder order) {
        return repository.update(id, order);
    }

    public void deleteOrder(Long id) {
        repository.delete(id);
    }
}
