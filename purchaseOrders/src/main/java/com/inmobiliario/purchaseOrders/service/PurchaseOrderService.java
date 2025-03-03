package com.inmobiliario.purchaseOrders.service;

import com.inmobiliario.purchaseOrders.client.CartClient;
import com.inmobiliario.purchaseOrders.dto.ApiResponse;
import com.inmobiliario.purchaseOrders.dto.Cart;
import com.inmobiliario.purchaseOrders.exceptions.GenerateServiceException;
import com.inmobiliario.purchaseOrders.model.PurchaseOrder;
import com.inmobiliario.purchaseOrders.repository.PurchaseOrderRepository;

import org.springframework.stereotype.Service;
import com.inmobiliario.purchaseOrders.validator.PurchaseOrderValidator;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final CartClient cartClient;

    PurchaseOrderService(PurchaseOrderRepository repository, CartClient cartClient) {
        this.repository = repository;
        this.cartClient = cartClient;
    }

    public PurchaseOrder createOrder(PurchaseOrder order) {
        PurchaseOrderValidator.validateCreate(order);
        if(cartExists(order.getCartId())){return repository.save(order);}else{
            throw new GenerateServiceException("Cart no exist");
        }
        
    }

    public List<PurchaseOrder> getAllOrders() {
        return repository.findAll();
    }

    public Optional<PurchaseOrder> getOrderById(Long id) {
        PurchaseOrderValidator.validateId(id);
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
        PurchaseOrderValidator.validateUpdate(id, order);
        return repository.update(id, order);
    }

    public void deleteOrder(Long id) {
        repository.delete(id);
    }

    boolean cartExists(Long cartId) {
        try {
            ApiResponse<Cart> cart = cartClient.getCartById(cartId);
            return cart != null;
        } catch (Exception e) {
            return false;
        }
}
}