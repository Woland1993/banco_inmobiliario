package com.inmobiliario.payment.repository;

import com.inmobiliario.payment.exceptions.*;
import com.inmobiliario.payment.model.*;
import com.inmobiliario.payment.validator.PaymentValidator;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public List<Payment> findAll() {
        try {
            if (payments.isEmpty()) {
                throw new NoDataFoundException("No payments found.");
            }
            return payments;
        } catch (Exception e) {
            throw new GenerateServiceException("Error retrieving payment list");
        }
    }

    public Optional<Payment> findById(Long id) {

        try {
            return payments.stream()
                    .filter(p -> p.getPaymentId().equals(id))
                    .findFirst()
                    .or(() -> {
                        throw new NoDataFoundException("Payment with ID: " + id + " not found.");
                    });
        } catch (Exception e) {
            throw new GenerateServiceException("Error searching for payment");
        }
    }

    public void save(Payment payment) {
        PaymentValidator.validatePayment(payment);
        try {
           
            // Check if a payment with the same ID already exists
            if (payments.stream().anyMatch(p -> p.getPaymentId().equals(payment.getPaymentId()))) {
                throw new ValidateServiceException("A payment with the same ID already exists: " + payment.getPaymentId());
            }
    
            payments.add(payment);
        } catch (ValidateServiceException e) {
            throw e; // Re-throw validation exception
        } catch (Exception e) {
            throw new GenerateServiceException("Error saving payment");
        }
    }

    public void update(Payment payment) {
        PaymentValidator.validatePayment(payment);

        try {
           
            if (findById(payment.getPaymentId()).isEmpty()) {
                throw new NoDataFoundException("Payment with ID: " + payment.getPaymentId() + " not found for update.");
            }
            deleteById(payment.getPaymentId());
            save(payment);
        } catch (Exception e) {
            throw new GenerateServiceException("Error updating payment");
        }
    }

    public void deleteById(Long id) {
        PaymentValidator.validatePaymentId(id);

        try {
            boolean removed = payments.removeIf(p -> p.getPaymentId().equals(id));
            if (!removed) {
                throw new NoDataFoundException("Payment with ID: " + id + " not found for deletion.");
            }
        } catch (Exception e) {
            throw new GenerateServiceException("Error deleting payment");
        }
    }
}
