package by.teachmeskills.commands;

import by.teachmeskills.entities.Product;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.impl.CategoryServiceImpl;
import by.teachmeskills.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectProductPageCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);

    private final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String productId = request.getParameter(PRODUCT_ID.getValue());
        try {
            Product product = productService.findById(Integer.parseInt(productId));
            request.setAttribute(PRODUCT.getValue(), product);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return PRODUCT_PAGE.getPath();
    }
}