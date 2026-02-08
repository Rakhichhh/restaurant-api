package com.restaurant.restaurantapi.patterns.factory;

public final class OrderFactory {
    private OrderFactory() {}

    public static void validateTypeSpecificFields(String type, String address, Integer tableNumber) {
        String t = type == null ? "" : type.toUpperCase();
        switch (t) {
            case "DELIVERY" -> {
                if (address == null || address.isBlank())
                    throw new IllegalArgumentException("address is required for DELIVERY");
            }
            case "DINE_IN" -> {
                if (tableNumber == null || tableNumber <= 0)
                    throw new IllegalArgumentException("tableNumber must be > 0 for DINE_IN");
            }
            default -> throw new IllegalArgumentException("Unsupported orderType: " + type);
        }
    }
}
