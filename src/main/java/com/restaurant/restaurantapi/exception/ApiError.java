package com.restaurant.restaurantapi.exception;

import java.time.LocalDateTime;

public class ApiError {
    public final String message;
    public final String path;
    public final LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(String message, String path) {
        this.message = message;
        this.path = path;
    }
}