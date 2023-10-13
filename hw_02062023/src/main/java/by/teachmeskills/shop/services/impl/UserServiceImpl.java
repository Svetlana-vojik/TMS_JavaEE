package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.PagesPathEnum;
import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.entities.Order;
import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.repositories.UserRepository;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.OrderService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.teachmeskills.shop.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.shop.PagesPathEnum.USER_PROFILE_PAGE;
import static by.teachmeskills.shop.ShopConstants.ADDRESS;
import static by.teachmeskills.shop.ShopConstants.BIRTHDAY;
import static by.teachmeskills.shop.ShopConstants.CATEGORIES;
import static by.teachmeskills.shop.ShopConstants.EMAIL;
import static by.teachmeskills.shop.ShopConstants.NAME;
import static by.teachmeskills.shop.ShopConstants.ORDERS;
import static by.teachmeskills.shop.ShopConstants.SURNAME;
import static by.teachmeskills.shop.ShopConstants.USER;
import static by.teachmeskills.shop.utils.ValidatorUtil.validateUserData;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;


    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, ProductService productService, OrderService orderService) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public User create(User entity) {
        return userRepository.create(entity);
    }

    @Override
    public ModelAndView createUser(User entity) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.REGISTRATION_PAGE.getPath());
        Map<String, String> data = Map.of(NAME, entity.getName(), SURNAME, entity.getSurname(), BIRTHDAY,
                String.valueOf(entity.getBirthday()), EMAIL, entity.getEmail(),
                ADDRESS, entity.getAddress());
        if (validateUserData(data)) {
            if ((userRepository.findByEmailAndPassword(entity.getEmail(), entity.getPassword())) != null) {
                modelAndView.addObject("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
            } else {
                userRepository.create(entity);
                modelAndView.addObject("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
            }
        } else {
            modelAndView.addObject("info", "Некорректные данные.");
        }
        return modelAndView;
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public ModelAndView authenticate(User user) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                List<Category> categoriesList = categoryService.read();
                modelMap.addAttribute(CATEGORIES, categoriesList);
                modelAndView.setViewName(HOME_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
            } else {
                modelMap.addAttribute("error", "Пользователь не зарегистрирован!");
                return new ModelAndView(LOGIN_PAGE.getPath(), modelMap);
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView generateAccountPage(User user) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                modelMap.addAttribute(USER, loggedUser);
                List<Order> orders = orderService.getOrdersByUserId(user.getId());
                modelMap.addAttribute(ORDERS, orders);
                modelAndView.addAllObjects(modelMap);
            }
        } else {
            modelMap.addAttribute("info", "Для входа в кабинет введите почту и пароль!");
            modelAndView.addAllObjects(modelMap);
        }
        return new ModelAndView(USER_PROFILE_PAGE.getPath(), modelMap);
    }
}