package com.inmobiliario.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long paymentId;
    private Long orderId;
    private PaymentMethod method;
    private PaymentStatus status;
   
}