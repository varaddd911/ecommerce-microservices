package com.example.product_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products") // Optional: for naming table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private Integer quantityAvailable;

    // Constructors (required by JPA)
    public Product() {}

    public Product(String name, Double price, String description, Integer quantityAvailable) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
}
