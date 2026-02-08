package com.restaurant.restaurantapi.model;

public class DineInOrder extends BaseOrder {
    private final Integer tableNumber;

    public DineInOrder(String customerName, Integer tableNumber) {
        super(customerName);
        this.tableNumber = tableNumber;
    }

    public Integer getTableNumber() { return tableNumber; }
    @Override public String getType() { return "DINE_IN"; }
}
