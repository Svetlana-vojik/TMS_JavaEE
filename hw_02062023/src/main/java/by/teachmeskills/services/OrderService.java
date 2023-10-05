package by.teachmeskills.services;

import by.teachmeskills.entities.Order;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    Order findById(int id);
    List<Order> findByUserId(int id);
}