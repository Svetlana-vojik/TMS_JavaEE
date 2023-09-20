package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.Cart;
import by.teachmeskills.model.Product;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class AddProductToCartCommand implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());

        try {
            Product product = CRUDUtils.getProductById(productId);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addProduct(product);
            session.setAttribute("cart", cart);
            request.setAttribute("product", product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}