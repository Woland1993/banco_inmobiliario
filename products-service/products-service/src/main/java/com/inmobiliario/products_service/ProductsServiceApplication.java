package com.inmobiliario.products_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients 
@OpenAPIDefinition(info = @Info(title = "pruducts API", version = "1.0", description = "API for managing products"))

public class ProductsServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

}
