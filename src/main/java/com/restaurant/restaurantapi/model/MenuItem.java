package com.restaurant.restaurantapi.model;

import java.math.BigDecimal;

public class MenuItem {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean isAvailable;

    public MenuItem() {}

    public MenuItem(Long id, String name, BigDecimal price, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean available) { isAvailable = available; }
}
