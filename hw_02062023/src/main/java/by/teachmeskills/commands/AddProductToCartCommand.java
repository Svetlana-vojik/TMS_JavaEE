package by.teachmeskills.commands;

import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Product;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class AddProductToCartCommand implements BaseCommand {
    private final static Logger log = LogManager.getLogger(AddProductToCartCommand.class);
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());

        try {
            Product product = productService.findById(Integer.parseInt(productId));
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addProduct(product);
            session.setAttribute("cart", cart);
            request.setAttribute("product", product);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return PagesPathEnum.PRODUCT_PAGE.getPath();
    }
}