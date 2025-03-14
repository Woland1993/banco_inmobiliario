package com.inmobiliario.products_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NO_CONTENT)
public class NoDataFoundException extends RuntimeException {
    
    private static final long serialVersionUID=1L;


      public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message,cause);
    }

    public NoDataFoundException(String message, Throwable cause,
    boolean enableSuppression,
    boolean writableStackTrace) {
        super( message, cause,
         enableSuppression,
         writableStackTrace);
    }
}
