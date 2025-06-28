package com.example.product_service.entity;

import jakarta.persistence.*;

/**
 * Entity representing a product in the system.
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private Integer quantityAvailable;

    /**
     * Default constructor required by JPA.
     */
    public Product() {}

    /**
     * Constructs a Product with the given details.
     * @param name the product name
     * @param price the product price
     * @param description the product description
     * @param quantityAvailable the available quantity
     */
    public Product(String name, Double price, String description, Integer quantityAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
