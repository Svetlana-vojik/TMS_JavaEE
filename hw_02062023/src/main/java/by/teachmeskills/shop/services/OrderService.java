package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Order;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    ModelAndView findById(int id);
    List<Order> findByUserId(int id);
}