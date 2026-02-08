package com.restaurant.restaurantapi.patterns.singleton;

import java.time.LocalDateTime;

public final class LoggingService {
    private static volatile LoggingService instance;

    private LoggingService() {}

    public static LoggingService getInstance() {
        if (instance == null) {
            synchronized (LoggingService.class) {
                if (instance == null) instance = new LoggingService();
            }
        }
        return instance;
    }

    public void info(String msg) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + msg);
    }
}
