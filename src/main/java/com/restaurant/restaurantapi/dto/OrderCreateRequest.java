package com.restaurant.restaurantapi.dto;

import java.util.List;

public class OrderCreateRequest {
    public String customerName;
    public String orderType;
    public String address;
    public Integer tableNumber;
    public List<Item> items;

    public static class Item {
        public Long menuItemId;
        public Integer quantity;
    }
}
