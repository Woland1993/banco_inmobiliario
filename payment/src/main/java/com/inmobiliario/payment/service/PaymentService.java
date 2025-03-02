package com.inmobiliario.payment.service;

import com.inmobiliario.payment.model.*;
import com.inmobiliario.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import com.inmobiliario.payment.client.OrderClient;
import org.springframework.stereotype.Service;
import com.inmobiliario.payment.dto.OrderUpdateRequest;


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
        // Guardar el pago en la lista (simulación de base de datos)
        paymentRepository.save(payment);

        // Llamar al microservicio de órdenes para actualizar el estado
        updateOrderWithPayment(payment);
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
            System.err.println("Error updating order: " + e.getMessage());
        }
    }
}