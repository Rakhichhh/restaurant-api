package com.restaurant.restaurantapi.controller;

import com.restaurant.restaurantapi.dto.OrderCreateRequest;
import com.restaurant.restaurantapi.model.Order;
import com.restaurant.restaurantapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody OrderCreateRequest req) {
        Long id = service.create(req);
        return Map.of("orderId", id);
    }


    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return service.getById(id);
    }
}