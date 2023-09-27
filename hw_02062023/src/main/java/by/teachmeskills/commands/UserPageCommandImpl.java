package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.Order;
import by.teachmeskills.model.Product;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserPageCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<Product> productList1 = new ArrayList<>();
        List<Product> productList2 = new ArrayList<>();
        productList1.add(CRUDUtils.getProductById("1"));
        productList2.add(CRUDUtils.getProductById("4"));
        productList2.add(CRUDUtils.getProductById("5"));

        Order order1 = new Order(1, LocalDate.now(), productList1);
        Order order2 = new Order(2, LocalDate.now(), productList2);
        List<Order> userOrders = new ArrayList<>();
        userOrders.add(order1);
        userOrders.add(order2);
        request.setAttribute("userOrders", userOrders);
        return PagesPathEnum.USER_PROFILE_PAGE.getPath();
    }
}