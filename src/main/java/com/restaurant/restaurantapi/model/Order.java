package com.restaurant.restaurantapi.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private final String customerName;
    private final String orderType;
    private final String status;
    private final LocalDateTime createdAt;
    private final List<OrderItem> items;

    private Order(Builder b) {
        this.id = b.id;
        this.customerName = b.customerName;
        this.orderType = b.orderType;
        this.status = b.status;
        this.createdAt = b.createdAt;
        this.items = b.items;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public String getOrderType() { return orderType; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return items; }

    public static class Builder {
        private Long id;
        private String customerName;
        private String orderType;
        private String status;
        private LocalDateTime createdAt;
        private List<OrderItem> items;

        public Builder customerName(String v) { this.customerName = v; return this; }
        public Builder orderType(String v) { this.orderType = v; return this; }
        public Builder status(String v) { this.status = v; return this; }
        public Builder createdAt(LocalDateTime v) { this.createdAt = v; return this; }
        public Builder items(List<OrderItem> v) { this.items = v; return this; }

        public Order build() {
            if (customerName == null || orderType == null || status == null || createdAt == null)
                throw new IllegalStateException("Missing required fields for Order");
            return new Order(this);
        }
    }
}
