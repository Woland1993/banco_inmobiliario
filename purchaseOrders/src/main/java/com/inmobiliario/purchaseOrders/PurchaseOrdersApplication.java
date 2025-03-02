package com.inmobiliario.purchaseOrders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Purchase Orders API", version = "1.0", description = "API for managing purchase orders"))
public class PurchaseOrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(PurchaseOrdersApplication.class, args);
    }
}
