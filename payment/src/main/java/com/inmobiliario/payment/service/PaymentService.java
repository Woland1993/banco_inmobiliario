package com.inmobiliario.payment.service;

import com.inmobiliario.payment.model.*;
import com.inmobiliario.payment.repository.PaymentRepository;
import com.inmobiliario.payment.validator.PaymentValidator;

import org.springframework.stereotype.Service;
import com.inmobiliario.payment.client.OrderClient;
import com.inmobiliario.payment.dto.OrderUpdateRequest;
import com.inmobiliario.payment.exceptions.GenerateServiceException;
import com.inmobiliario.payment.exceptions.ValidateServiceException;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    public PaymentService(PaymentRepository paymentRepository, OrderClient orderClient) {
        this.paymentRepository = paymentRepository;
        this.orderClient = orderClient;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }
   public void createPayment(Payment payment) {
        PaymentValidator.validatePayment(payment);
        try {
            updateOrderWithPayment(payment);
            paymentRepository.save(payment);
        } catch (ValidateServiceException e) {
            throw e; // Re-throw validation exception
        } catch (Exception e) {
            throw new GenerateServiceException("Error creating payment", e);
        }
    }

    public void updatePayment(Payment payment) {
        paymentRepository.update(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private void updateOrderWithPayment(Payment payment) {
        OrderUpdateRequest orderUpdate = new OrderUpdateRequest(payment.getId(), "PAID");

        try {
            orderClient.updateOrder(payment.getOrderId(), orderUpdate);
            System.out.println("Order updated successfully: " + payment.getOrderId());
        } catch (Exception e) {
            throw new GenerateServiceException("Error updating order for payment ID: " + payment.getId(), e);
        }
    }
}