package com.inmobiliario.shoppingCart.config;

import com.inmobiliario.shoppingCart.dto.ApiResponse;
import com.inmobiliario.shoppingCart.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import jakarta.servlet.http.HttpServletRequest; 

@ControllerAdvice
public class ErrorHandlerConfig {

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<ApiResponse<Object>> validateServiceHandler(ValidateServiceException e, HttpServletRequest request) {
        ApiResponse<Object> response = new ApiResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ApiResponse<Object>> noDataFoundHandler(NoDataFoundException e, HttpServletRequest request) {
        ApiResponse<Object> response = new ApiResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(GenerateServiceException.class)
    public ResponseEntity<ApiResponse<Object>> generateServiceHandler(GenerateServiceException e, HttpServletRequest request) {
        ApiResponse<Object> response = new ApiResponse<>(e.getMessage(), false, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> allHandler(Exception e, HttpServletRequest request) {
        ApiResponse<Object> response = new ApiResponse<>("Internal Server Error", false, null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
