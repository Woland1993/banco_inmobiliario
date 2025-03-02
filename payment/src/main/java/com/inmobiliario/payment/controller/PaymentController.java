package com.inmobiliario.payment.controller;

import com.inmobiliario.payment.service.PaymentService;
import com.inmobiliario.payment.model.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Tag(name = "Payment API", description = "Endpoints for managing payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get all payments", description = "Retrieves all payments stored in the system.")
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @Operation(summary = "Get payment by ID", description = "Retrieves a payment by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new payment", description = "Stores a new payment in the system.")
    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
        return ResponseEntity.ok("Payment created and order updated successfully");
    }

  /*   @Operation(summary = "Update an existing payment", description = "Updates an existing payment by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        if (paymentService.getPaymentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        payment.setId(id);
        paymentService.updatePayment(payment);
        return ResponseEntity.ok("Payment updated successfully");
    }

    @Operation(summary = "Delete a payment", description = "Deletes a payment by its unique ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        if (paymentService.getPaymentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }
        */
}
