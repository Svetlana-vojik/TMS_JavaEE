package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.repositories.OrderRepository;
import by.teachmeskills.shop.repositories.impl.OrderRepositoryImpl;
import by.teachmeskills.shop.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public Order create(Order entity) {
        return orderRepository.create(entity);
    }

    @Override
    public List<Order> read() {
        return orderRepository.read();
    }

    @Override
    public Order update(Order entity) {
        return orderRepository.update(entity);
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

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return orderRepository.findByUserId(id);
    }
}