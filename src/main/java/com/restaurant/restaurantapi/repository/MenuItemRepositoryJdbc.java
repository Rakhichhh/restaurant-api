package com.restaurant.restaurantapi.repository;

import com.restaurant.restaurantapi.model.MenuItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MenuItemRepositoryJdbc {

    private final JdbcTemplate jdbc;

    public MenuItemRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<MenuItem> findAll() {
        String sql = "select id, name, price, is_available from menu_items order by id";
        return jdbc.query(sql, (rs, rowNum) ->
                new MenuItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getBoolean("is_available")
                )
        );
    }

    public Optional<MenuItem> findById(Long id) {
        String sql = "select id, name, price, is_available from menu_items where id = ?";
        List<MenuItem> list = jdbc.query(sql, (rs, rowNum) ->
                new MenuItem(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getBoolean("is_available")
                ), id);
        return list.stream().findFirst();
    }

    public MenuItem save(MenuItem item) {
        String sql = "insert into menu_items(name, price, is_available) values (?, ?, ?)";
        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.setBigDecimal(2, item.getPrice());
            ps.setBoolean(3, Boolean.TRUE.equals(item.getIsAvailable()));
            return ps;
        }, kh);

        Long id = Objects.requireNonNull(kh.getKey()).longValue();
        item.setId(id);
        return item;
    }

    public MenuItem update(Long id, MenuItem item) {
        String sql = "update menu_items set name = ?, price = ?, is_available = ? where id = ?";
        jdbc.update(sql,
                item.getName(),
                item.getPrice(),
                Boolean.TRUE.equals(item.getIsAvailable()),
                id
        );
        item.setId(id);
        return item;
    }

    public void delete(Long id) {
        jdbc.update("delete from menu_items where id = ?", id);
    }
}