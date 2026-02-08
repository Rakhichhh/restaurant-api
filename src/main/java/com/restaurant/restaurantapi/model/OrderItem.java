package com.restaurant.restaurantapi.model;

import java.math.BigDecimal;

public class OrderItem {
    private Long menuItemId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItem() {}

    public OrderItem(Long menuItemId, Integer quantity, BigDecimal unitPrice) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getMenuItemId() { return menuItemId; }
    public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
