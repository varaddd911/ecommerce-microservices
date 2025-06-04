package com.example.product_service.service;




import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    public boolean reduceQuantity(Long id, int amount) {
        Product p = getById(id);
        if (p == null) {
            System.out.println("Product with ID " + id + " not found."); // Add this log
            return false;
        }
        if (p.getQuantityAvailable() < amount) {
            System.out.println("Insufficient quantity for product ID " + id + ". Available: " + p.getQuantityAvailable() + ", Requested: " + amount); // Add this log
            return false;
        }
        // If we reach here, quantity can be reduced
        p.setQuantityAvailable(p.getQuantityAvailable() - amount);
        repo.save(p);
        return true;
    }

    public List<Product> getAll() { return repo.findAll(); }
    public Product getById(Long id) { return repo.findById(id).orElse(null); }
    public Product create(Product p) { return repo.save(p); }
    public Product update(Long id, Product newData) {
        Product p = getById(id);
        if (p != null) {
            p.setName(newData.getName());
            p.setPrice(newData.getPrice());
            p.setDescription(newData.getDescription());
            p.setQuantityAvailable(newData.getQuantityAvailable());
            return repo.save(p);
        }
        return null;
    }
}
