package com.example.order_service.client;

import com.example.order_service.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public ProductDto getProductById(Long id) {
        System.out.println("Product Service is unavailable. Fallback executed.");
        return null; // Or return a default Product object if you want
    }

    @Override
    public String reduceQuantity(Long id, int amount) {
        return "Failed to reduce quantity. Product service is down.";
    }
}
