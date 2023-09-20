package by.teachmeskills.commands;

import by.teachmeskills.model.Product;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectProductPageCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String productId = request.getParameter(PRODUCT_ID.getValue());
        try {
            Product product = CRUDUtils.getProductById(productId);
            request.setAttribute(PRODUCT.getValue(), product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return PRODUCT_PAGE.getPath();
    }
}