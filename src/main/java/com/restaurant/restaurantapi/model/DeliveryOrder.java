package com.restaurant.restaurantapi.model;

public class DeliveryOrder extends BaseOrder {
    private final String address;

    public DeliveryOrder(String customerName, String address) {
        super(customerName);
        this.address = address;
    }

    public String getAddress() { return address; }
    @Override public String getType() { return "DELIVERY"; }
}
