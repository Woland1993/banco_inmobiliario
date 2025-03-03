package com.inmobiliario.payment.validator;

import com.inmobiliario.payment.model.Payment;
import com.inmobiliario.payment.model.PaymentMethod;
import com.inmobiliario.payment.model.PaymentStatus;
import com.inmobiliario.payment.exceptions.ValidateServiceException;

public class PaymentValidator {

    public static void validatePayment(Payment payment) {
        if (payment == null) {
            throw new ValidateServiceException("Payment cannot be null");
        }

        if (payment.getOrderId() == null) {
            throw new ValidateServiceException("Order ID is required");
        }

        if (payment.getMethod() == null) {
            throw new ValidateServiceException("Payment method is required");
        } else if (!isValidPaymentMethod(payment.getMethod())) {
            throw new ValidateServiceException("Invalid payment method");
        }

        if (payment.getStatus() == null) {
            throw new ValidateServiceException("Payment status is required");
        } else if (!isValidPaymentStatus(payment.getStatus())) {
            throw new ValidateServiceException("Invalid payment status");
        }
    }

    public static void validatePaymentId(Long id) {
        if (id == null || id <= 0) {
            throw new ValidateServiceException("Payment ID must be a valid number");
        }
    }

    private static boolean isValidPaymentMethod(PaymentMethod method) {
        for (PaymentMethod validMethod : PaymentMethod.values()) {
            if (validMethod == method) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidPaymentStatus(PaymentStatus status) {
        for (PaymentStatus validStatus : PaymentStatus.values()) {
            if (validStatus == status) {
                return true;
            }
        }
        return false;
    }
}
