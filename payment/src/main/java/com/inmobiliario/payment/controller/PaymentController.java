package com.inmobiliario.payment.controller;

import com.inmobiliario.payment.dto.ApiResponse;
import com.inmobiliario.payment.service.PaymentService;
import com.inmobiliario.payment.model.Payment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?>  getAllPayments() {
    return  new ApiResponse<>("successfully", true, paymentService.getAllPayments()).createResponse(HttpStatus.OK);
    }
   
    @Operation(summary = "Get payment by ID", description = "Retrieves a payment by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Payment>> getPaymentById(@PathVariable Long id) {
        Optional<Payment> paymentOptional = paymentService.getPaymentById(id);
    
        if (paymentOptional.isPresent()) {
            return new ApiResponse<>("Payment retrieved successfully", true, paymentOptional.get())
                    .createResponse(HttpStatus.OK);
        } else {
            return new ApiResponse<Payment>("Payment not found", false, null) // Especifica <Payment>
                    .createResponse(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new payment", description = "Stores a new payment in the system.")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
        return new ApiResponse<>("Payment created and order updated successfully", true, "Payment successful")
                .createResponse(HttpStatus.CREATED);
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
