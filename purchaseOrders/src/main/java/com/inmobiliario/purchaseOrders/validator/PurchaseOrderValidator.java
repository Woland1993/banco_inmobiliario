package com.inmobiliario.purchaseOrders.validator;

import com.inmobiliario.purchaseOrders.exceptions.ValidateServiceException;
import com.inmobiliario.purchaseOrders.model.PurchaseOrder;

public class PurchaseOrderValidator {

    public static void validateCreate(PurchaseOrder order) {
        if (order.getCustomerId() == null || order.getCustomerId() <= 0) {
            throw new ValidateServiceException("Customer ID is required and must be greater than zero.");
        }
        if (order.getCartId() == null || order.getCartId() <= 0) {
            throw new ValidateServiceException("Cart ID is required and must be greater than zero.");
        }
        if (order.getTotalAmount() <= 0) {
            throw new ValidateServiceException("Total amount must be greater than zero.");
        }
       
    }

    public static void validateUpdate(Long id, PurchaseOrder order) {
        if (id == null || id <= 0) {
            throw new ValidateServiceException("Order ID is required and must be greater than zero.");
        }
        if (order.getPaymentId() == null || order.getPaymentId() <= 0) {
            throw new ValidateServiceException("Payment ID is required and must be greater than zero.");
        }
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            throw new ValidateServiceException("Status is required.");
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidateServiceException("Order ID is required and must be greater than zero.");
        }
    }

    public static void validateStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new ValidateServiceException("Status is required.");
        }
    }
}
