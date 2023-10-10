package by.teachmeskills.services;

import by.teachmeskills.entities.User;
import org.springframework.web.servlet.ModelAndView;

public interface UserService extends BaseService<User> {
    User findById(int id);

    User findByEmailAndPassword(String email, String password);

    ModelAndView authenticate(User user);

    ModelAndView findUserOrders(User user);
}