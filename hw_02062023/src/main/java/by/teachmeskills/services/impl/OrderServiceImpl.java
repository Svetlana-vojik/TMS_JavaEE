package by.teachmeskills.services.impl;

import by.teachmeskills.entities.Order;
import by.teachmeskills.repositories.OrderRepository;
import by.teachmeskills.repositories.impl.OrderRepositoryImpl;
import by.teachmeskills.services.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public ModelAndView create(Order entity) {
        return null;
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
    public ModelAndView findById(int id) {
        return new ModelAndView();
    }

    @Override
    public List<Order> findByUserId(int id) {
        return orderRepository.findByUserId(id);
    }
}