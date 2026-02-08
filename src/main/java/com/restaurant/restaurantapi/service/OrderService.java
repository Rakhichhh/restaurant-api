package com.restaurant.restaurantapi.service;

import com.restaurant.restaurantapi.dto.OrderCreateRequest;
import com.restaurant.restaurantapi.model.MenuItem;
import com.restaurant.restaurantapi.model.Order;
import com.restaurant.restaurantapi.model.OrderItem;
import com.restaurant.restaurantapi.patterns.factory.OrderFactory;
import com.restaurant.restaurantapi.patterns.singleton.LoggingService;
import com.restaurant.restaurantapi.repository.MenuItemRepositoryJdbc;
import com.restaurant.restaurantapi.repository.OrderRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepositoryJdbc orderRepo;
    private final MenuItemRepositoryJdbc menuRepo;
    private final LoggingService log = LoggingService.getInstance();

    public OrderService(OrderRepositoryJdbc orderRepo, MenuItemRepositoryJdbc menuRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
    }

    public Long create(OrderCreateRequest req) {
        if (req.customerName == null || req.customerName.isBlank())
            throw new IllegalArgumentException("customerName is required");
        if (req.orderType == null || req.orderType.isBlank())
            throw new IllegalArgumentException("orderType is required");
        if (req.items == null || req.items.isEmpty())
            throw new IllegalArgumentException("items are required");

        // Factory usage
        OrderFactory.validateTypeSpecificFields(req.orderType, req.address, req.tableNumber);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderCreateRequest.Item i : req.items) {
            if (i.menuItemId == null) throw new IllegalArgumentException("menuItemId is required");
            if (i.quantity == null || i.quantity <= 0) throw new IllegalArgumentException("quantity must be > 0");

            MenuItem menu = menuRepo.findById(i.menuItemId)
                    .orElseThrow(() -> new IllegalArgumentException("MenuItem not found: " + i.menuItemId));

            orderItems.add(new OrderItem(i.menuItemId, i.quantity, menu.getPrice()));
        }

        // Builder usage
        Order order = new Order.Builder()
                .customerName(req.customerName)
                .orderType(req.orderType.toUpperCase())
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .items(orderItems)
                .build();

        Long id = orderRepo.insertOrder(order);
        orderRepo.insertItems(id, orderItems);

        log.info("Order created id=" + id + ", type=" + order.getOrderType());
        return id;
    }


    public Order getById(Long id) {
        Order o = orderRepo.findOrderWithItems(id);
        if (o == null) throw new IllegalArgumentException("Order not found: " + id);
        return o;
    }
}