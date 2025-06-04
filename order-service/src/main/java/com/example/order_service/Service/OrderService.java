package com.example.order_service.Service;


import com.example.order_service.Entity.OrderEntity;
import com.example.order_service.Repository.OrderRepository;
import com.example.order_service.Entity.OrderEntity;

import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public OrderEntity placeOrder(OrderEntity order) {
        ProductDto product = productClient.getProductById(order.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        if (product.getQuantityAvailable() < order.getQuantity()) {
            throw new RuntimeException("Not enough quantity available");
        }

        // Reduce quantity in product service
        try {
            boolean result = Boolean.parseBoolean(productClient.reduceQuantity(order.getProductId(),order.getQuantity()));
            if (!result) {
                throw new RuntimeException("Failed to reduce product quantity");
            }
        } catch (Exception e) {
            System.err.println("Error during product quantity reduction: " + e.getMessage());
            throw new RuntimeException("Failed to reduce product quantity", e);
        }

        order.setStatus("PLACED");
        return orderRepository.save(order);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
