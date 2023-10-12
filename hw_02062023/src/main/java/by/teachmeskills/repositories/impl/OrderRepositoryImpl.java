package by.teachmeskills.repositories.impl;

import by.teachmeskills.entities.Order;
import by.teachmeskills.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String CREATE_ORDER = "INSERT INTO shop.orders(date,price,userId";

    @Override
    public Order create(Order entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {
            statement.setTimestamp(1, Timestamp.valueOf(entity.getDate().atStartOfDay()));
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
        return null;
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findByUserId(int id) {
        return null;
    }
}