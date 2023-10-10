package by.teachmeskills.services.impl;

import by.teachmeskills.entities.Order;
import by.teachmeskills.entities.Product;
import by.teachmeskills.repositories.OrderRepository;
import by.teachmeskills.repositories.impl.OrderRepositoryImpl;
import by.teachmeskills.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository = new OrderRepositoryImpl();
    @Override
    public Product create(Order entity) {
        return orderRepository.create(entity);
    }

    @Override
    public List<Order> read() {
        return orderRepository.read();
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUserId(int id) {
        return orderRepository.findByUserId(id);
    }
}