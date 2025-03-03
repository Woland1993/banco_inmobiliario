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
                    .filter(p -> p.getId().equals(id))
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
            if (payments.stream().anyMatch(p -> p.getId().equals(payment.getId()))) {
                throw new ValidateServiceException("A payment with the same ID already exists: " + payment.getId());
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
           
            if (findById(payment.getId()).isEmpty()) {
                throw new NoDataFoundException("Payment with ID: " + payment.getId() + " not found for update.");
            }
            deleteById(payment.getId());
            save(payment);
        } catch (Exception e) {
            throw new GenerateServiceException("Error updating payment");
        }
    }

    public void deleteById(Long id) {
        PaymentValidator.validatePaymentId(id);

        try {
            boolean removed = payments.removeIf(p -> p.getId().equals(id));
            if (!removed) {
                throw new NoDataFoundException("Payment with ID: " + id + " not found for deletion.");
            }
        } catch (Exception e) {
            throw new GenerateServiceException("Error deleting payment");
        }
    }
}
