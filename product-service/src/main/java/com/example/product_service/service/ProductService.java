package com.example.product_service.service;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {
    private final ProductRepository repo;

    /**
     * Constructs a ProductService with the given repository.
     * @param repo the product repository
     */
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    /**
     * Reduces the quantity of a product by a specified amount.
     * @param id the product ID
     * @param amount the amount to reduce
     * @return true if successful, false otherwise
     */
    public boolean reduceQuantity(Long id, int amount) {
        Product product = getById(id);
        if (product == null) {
            System.out.println("Product with ID " + id + " not found.");
            return false;
        }
        if (product.getQuantityAvailable() < amount) {
            System.out.println("Insufficient quantity for product ID " + id + ". Available: " + product.getQuantityAvailable() + ", Requested: " + amount);
            return false;
        }
        product.setQuantityAvailable(product.getQuantityAvailable() - amount);
        repo.save(product);
        return true;
    }

    /**
     * Retrieves all products.
     * @return list of products
     */
    public List<Product> getAll() {
        return repo.findAll();
    }

    /**
     * Retrieves a product by its ID.
     * @param id the product ID
     * @return the product, or null if not found
     */
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    /**
     * Creates a new product.
     * @param product the product to create
     * @return the created product
     */
    public Product create(Product product) {
        return repo.save(product);
    }

    /**
     * Updates an existing product.
     * @param id the product ID
     * @param newData the new product data
     * @return the updated product, or null if not found
     */
    public Product update(Long id, Product newData) {
        Product product = getById(id);
        if (product != null) {
            product.setName(newData.getName());
            product.setPrice(newData.getPrice());
            product.setDescription(newData.getDescription());
            product.setQuantityAvailable(newData.getQuantityAvailable());
            return repo.save(product);
        }
        return null;
    }
}
