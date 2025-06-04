package com.example.order_service.client;

import com.example.order_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);

    @PostMapping("/products/{id}/reduceQuantity")
    String reduceQuantity(@PathVariable Long id, @RequestParam int quantity);

}
