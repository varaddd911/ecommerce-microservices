// product-service/Controller/ProductController.java
package com.example.product_service.Controller;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) { return service.create(p); }

    @GetMapping
    public List<Product> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) { return service.getById(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p) {
        return service.update(id, p);
    }

    @PostMapping("/{id}/reduceQuantity")
    public ResponseEntity<String> reduceQuantity(@PathVariable Long id, @RequestParam int quantity) {
        boolean success = service.reduceQuantity(id, quantity);
        if (success) {
            return ResponseEntity.ok("true"); // <--- CHANGE THIS to "true"
        } else {
            // For a BAD_REQUEST, returning "false" is fine, but checking the HTTP status is better on the client side.
            // For now, let's keep it simple and consistent with client's Boolean.parseBoolean expectation.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("false"); // <--- CHANGE THIS to "false"
        }
    }
}