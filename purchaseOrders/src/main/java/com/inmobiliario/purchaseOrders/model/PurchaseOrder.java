package com.inmobiliario.purchaseOrders.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    private Long id;
    private Long customerId;
    private Long cartId;
    private Long paymentId;
    private double totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}