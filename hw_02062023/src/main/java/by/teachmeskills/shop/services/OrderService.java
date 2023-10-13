package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Order;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    Order findById(int id);
    List<Order> findByUserId(int id);
    List<Order> getOrdersByUserId(int id);
}