package by.teachmeskills.services;

import by.teachmeskills.entities.Order;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    ModelAndView findById(int id);
    List<Order> findByUserId(int id);
}