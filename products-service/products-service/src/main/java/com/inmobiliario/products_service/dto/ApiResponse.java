package com.inmobiliario.products_service.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private T data;

    public ResponseEntity<ApiResponse<T>> createResponse(){
        return new ResponseEntity<>(this,HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this,status);
    }
}