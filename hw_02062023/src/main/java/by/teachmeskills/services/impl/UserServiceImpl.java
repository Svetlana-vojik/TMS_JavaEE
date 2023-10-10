package by.teachmeskills.services.impl;

import by.teachmeskills.entities.Category;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.User;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.repositories.CategoryRepository;
import by.teachmeskills.repositories.UserRepository;
import by.teachmeskills.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.repositories.impl.UserRepositoryImpl;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.UserService;
import by.teachmeskills.utils.ValidatorUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.enums.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.enums.PagesPathEnum.USER_PROFILE_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.CATEGORY;
import static by.teachmeskills.enums.ShopConstants.ORDERS;
import static by.teachmeskills.enums.ShopConstants.USER;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private final UserService userService = new UserServiceImpl();
    private final ProductService productService=new ProductServiceImpl();


    @Override
    public ModelAndView create(User entity) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.REGISTRATION_PAGE.getPath());

        if (ValidatorUtil.validateRegistration(entity.getEmail(), entity.getName(), entity.getSurname(), String.valueOf(entity.getBirthday()), entity.getAddress())) {
            if (userService.findByEmailAndPassword(entity.getEmail(), entity.getPassword()) != null) {
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
                List<Category> categoriesList = categoryRepository.read();
                modelMap.addAttribute(CATEGORY.getValue(), categoriesList);
                modelAndView.setViewName(HOME_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
            } else {
                modelMap.addAttribute("error", "Пользователь не зарегистрирован!");
                modelAndView.setViewName(LOGIN_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView findUserOrders(User user) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                List<Product> productList = productService.findByCategoryId(1);
                modelMap.addAttribute(USER, loggedUser);
                modelMap.addAttribute(ORDERS, productList);
                modelMap.addAttribute("date", "10-10-2023");
                modelMap.addAttribute("number", "#123-12-123");
                modelAndView.addAllObjects(modelMap);
            }
        } else {
            modelMap.addAttribute("info", "Для входа в личный кабинет введите почту и пароль!");
            modelAndView.addAllObjects(modelMap);
        }
        modelAndView.setViewName(USER_PROFILE_PAGE.getPath());
        return modelAndView;
    }
}