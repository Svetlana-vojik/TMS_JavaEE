package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String CREATE_ORDER = "INSERT INTO shop.orders(orderDate,price,userId";
    private static final String GET_ALL_ORDERS = "SELECT * FROM shop.orders";
    private static final String DELETE_ORDER = "DELETE FROM shop.orders WHERE id=?";
    private static final String UPDATE_ORDER = "UPDATE shop.orders WHERE id = ?";
    private static final String GET_ORDER = "SELECT * FROM shop.orders WHERE id=?";
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM shop.orders WHERE userId=?";

    @Override
    public Order create(Order entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {
            statement.setTimestamp(1, Timestamp.valueOf(entity.getOrderDate()));
            statement.setInt(2, entity.getPrice());
            statement.setInt(3, entity.getUserId());
            statement.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public List<Order> read() {
        List<Order> orderList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                orderList.add(Order.builder()
                        .id(set.getInt(1))
                        .orderDate(set.getTimestamp(2).toLocalDateTime())
                        .price(set.getInt(3))
                        .userId(set.getInt(4)).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public Order update(Order entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {
            statement.setInt(1, entity.getId());
            statement.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }


    @Override
    public Order findById(int id) {
        Order order = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            order = Order.builder()
                    .id(set.getInt(1))
                    .orderDate(set.getTimestamp(2).toLocalDateTime())
                    .price(set.getInt(3))
                    .userId(set.getInt(4)).build();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return order;
    }

    @Override
    public List<Order> findByUserId(int id) {
        List<Order> orderList = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            orderList.add(Order.builder()
                    .id(set.getInt(1))
                    .orderDate(set.getTimestamp(2).toLocalDateTime())
                    .price(set.getInt(3))
                    .userId(set.getInt(4)).build());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return orderList;
    }
}