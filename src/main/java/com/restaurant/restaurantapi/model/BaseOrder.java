package com.restaurant.restaurantapi.model;

public abstract class BaseOrder {
    protected final String customerName;

    protected BaseOrder(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() { return customerName; }
    public abstract String getType();
}
