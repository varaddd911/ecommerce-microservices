package com.example.product_service.repository;

import com.example.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
