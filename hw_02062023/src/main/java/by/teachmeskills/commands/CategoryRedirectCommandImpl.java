package by.teachmeskills.commands;

import by.teachmeskills.model.Product;
import by.teachmeskills.utils.CRUDUtils;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.teachmeskills.enums.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCTS;

public class CategoryRedirectCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String categoryId = request.getParameter(CATEGORY_ID.getValue());
        List<Product> products = CRUDUtils.getProductsByIdCategory(categoryId);
        request.setAttribute(PRODUCTS.getValue(), products);
        return CATEGORY_PAGE.getPath();
    }
}