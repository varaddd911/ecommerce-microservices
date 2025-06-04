package com.example.order_service.Controller;

import com.example.order_service.Entity.OrderEntity;
import com.example.order_service.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderEntity placeOrder(@RequestBody OrderEntity o) {
        return service.placeOrder(o);
    }

    @GetMapping
    public List<OrderEntity> getAll() {
        return service.getAllOrders();
    }
}
