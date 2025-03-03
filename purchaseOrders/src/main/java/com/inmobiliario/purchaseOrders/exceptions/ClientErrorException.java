package com.inmobiliario.purchaseOrders.exceptions;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message) {
        super(message);
    }
}