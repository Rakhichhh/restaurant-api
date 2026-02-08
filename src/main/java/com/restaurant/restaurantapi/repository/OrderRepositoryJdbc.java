package com.restaurant.restaurantapi.repository;

import com.restaurant.restaurantapi.model.Order;
import com.restaurant.restaurantapi.model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderRepositoryJdbc {

    private final JdbcTemplate jdbc;

    public OrderRepositoryJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long insertOrder(Order order) {
        String sql = "insert into orders(customer_name, order_type, status, created_at) values (?,?,?,?)";
        KeyHolder kh = new GeneratedKeyHolder();

        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getOrderType());
            ps.setString(3, order.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(order.getCreatedAt()));
            return ps;
        }, kh);

        return Objects.requireNonNull(kh.getKey()).longValue();
    }

    public void insertItems(Long orderId, List<OrderItem> items) {
        String sql = "insert into order_items(order_id, menu_item_id, quantity, unit_price) values (?,?,?,?)";
        for (OrderItem it : items) {
            jdbc.update(sql, orderId, it.getMenuItemId(), it.getQuantity(), it.getUnitPrice());
        }
    }

    public Order findOrderWithItems(Long id) {
        String orderSql = "select id, customer_name, order_type, status, created_at from orders where id = ?";
        var orders = jdbc.query(orderSql, (rs, rowNum) -> new Order.Builder()
                .customerName(rs.getString("customer_name"))
                .orderType(rs.getString("order_type"))
                .status(rs.getString("status"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .items(List.of())
                .build(), id);

        if (orders.isEmpty()) return null;

        Order order = orders.get(0);
        order.setId(id);

        String itemsSql = "select menu_item_id, quantity, unit_price from order_items where order_id = ?";
        List<OrderItem> items = jdbc.query(itemsSql, (rs, rowNum) ->
                new OrderItem(
                        rs.getLong("menu_item_id"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("unit_price")
                ), id);

        return new Order.Builder()
                .customerName(order.getCustomerName())
                .orderType(order.getOrderType())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(items)
                .build();
    }
}
