package com.inmobiliario.products_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateServiceException  extends RuntimeException{
    
    private static final long serialVersionUID=1L;


    public GenerateServiceException() {
        super();
    }

    public GenerateServiceException(String message) {
        super(message);
    }

    public GenerateServiceException(String message, Throwable cause) {
        super(message,cause);
    }

    public GenerateServiceException(String message, Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
        super( message, cause,
         enableSuppression,
         writableStackTrace);
    }
}
