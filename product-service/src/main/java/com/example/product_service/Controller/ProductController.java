// product-service/Controller/ProductController.java
package com.example.product_service.Controller;

import com.example.product_service.entity.Product;
import com.example.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    /**
     * Constructs a ProductController with the given ProductService.
     * @param service the product service
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Adds a new product.
     * @param product the product to add
     * @return the created product
     */
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.create(product);
    }

    /**
     * Retrieves all products.
     * @return list of products
     */
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    /**
     * Retrieves a product by its ID.
     * @param id the product ID
     * @return the product, or null if not found
     */
    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * Updates a product by its ID.
     * @param id the product ID
     * @param product the updated product data
     * @return the updated product, or null if not found
     */
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    /**
     * Reduces the quantity of a product.
     * @param id the product ID
     * @param quantity the quantity to reduce
     * @return ResponseEntity with "true" or "false"
     */
    @PostMapping("/{id}/reduceQuantity")
    public ResponseEntity<String> reduceQuantity(@PathVariable Long id, @RequestParam int quantity) {
        boolean success = service.reduceQuantity(id, quantity);
        if (success) {
            return ResponseEntity.ok("true");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("false");
        }
    }
}