package by.teachmeskills.commands;

import by.teachmeskills.entities.Order;
import by.teachmeskills.entities.Product;
import by.teachmeskills.entities.User;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserPageCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryRedirectCommandImpl.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Product> productList1 = new ArrayList<>();
        List<Product> productList2 = new ArrayList<>();

        try {
            productList1.add(productService.findById(1));
            productList2.add(productService.findById(4));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        Order order1 = new Order(1, LocalDate.now(), productList1);
        Order order2 = new Order(2, LocalDate.now(), productList2);
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(order1);
        userOrders.add(order2);
        request.setAttribute("userOrders", userOrders);
        return PagesPathEnum.USER_PROFILE_PAGE.getPath();
    }
}