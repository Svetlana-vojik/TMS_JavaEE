package by.teachmeskills.commands;

import by.teachmeskills.model.Product;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectProductPageCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        String productId = request.getParameter(PRODUCT_ID.getValue());
        try {
            Product product = CRUDUtils.getProductById(productId);
            request.setAttribute(PRODUCT.getValue(), product);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return PRODUCT_PAGE.getPath();
    }
}