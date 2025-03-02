package com.inmobiliario.api_gateway;



import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig  {

  @Bean
    public GroupedOpenApi productsApi() {
        return GroupedOpenApi.builder()
                .group("products-service")
                .pathsToMatch("/api/products/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info()
                        .title("Products API")
                        .description("API Gateway routes for Products Service")
                        .version("1.0")))
                .build();
    }
}
