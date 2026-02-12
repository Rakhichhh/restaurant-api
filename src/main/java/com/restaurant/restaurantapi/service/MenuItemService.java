package com.restaurant.restaurantapi.service;

import com.restaurant.restaurantapi.model.MenuItem;
import com.restaurant.restaurantapi.repository.MenuItemRepositoryJdbc;
import com.restaurant.restaurantapi.patterns.singleton.InMemoryCache;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepositoryJdbc repo;

    //singl cache
    private final InMemoryCache cache = InMemoryCache.getInstance();
    private static final String MENU_CACHE_KEY = "menu_all";

    public MenuItemService(MenuItemRepositoryJdbc repo) {
        this.repo = repo;
    }

    @SuppressWarnings("unchecked")
    public List<MenuItem> getAll() {
        Object cached = cache.get("menuItems");

        if (cached != null) {
            return (List<MenuItem>) cached;
        }

        List<MenuItem> items = repo.findAll();
        cache.put("menuItems", items);
        return items;
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

        MenuItem saved = repo.save(item);

        //invalid cache after chang
        cache.invalidate(MENU_CACHE_KEY);

        return saved;
    }

    public MenuItem update(Long id, MenuItem item) {
        getById(id);

        MenuItem updated = repo.update(id, item);

        //invalidate cache after change
        cache.invalidate(MENU_CACHE_KEY);

        return updated;
    }

    public void delete(Long id) {
        getById(id);
        repo.delete(id);

        //invalidate cache after change
        cache.invalidate(MENU_CACHE_KEY);
    }
}
