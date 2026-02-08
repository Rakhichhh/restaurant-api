package com.restaurant.restaurantapi.service;

import com.restaurant.restaurantapi.model.MenuItem;
import com.restaurant.restaurantapi.repository.MenuItemRepositoryJdbc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepositoryJdbc repo;

    public MenuItemService(MenuItemRepositoryJdbc repo) {
        this.repo = repo;
    }

    public List<MenuItem> getAll() {
        return repo.findAll();
    }

    public MenuItem getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("MenuItem not found: " + id));
    }

    public MenuItem create(MenuItem item) {
        if (item.getName() == null || item.getName().isBlank())
            throw new IllegalArgumentException("name is required");
        if (item.getPrice() == null)
            throw new IllegalArgumentException("price is required");
        if (item.getPrice().signum() <= 0)
            throw new IllegalArgumentException("price must be > 0");
        if (item.getIsAvailable() == null) item.setIsAvailable(true);

        return repo.save(item);
    }

    public MenuItem update(Long id, MenuItem item) {
        getById(id);
        return repo.update(id, item);
    }

    public void delete(Long id) {
        getById(id);
        repo.delete(id);
    }
}