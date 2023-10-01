package by.teachmeskills.commands;

import by.teachmeskills.entities.Category;
import by.teachmeskills.entities.Product;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.impl.CategoryServiceImpl;
import by.teachmeskills.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.teachmeskills.enums.PagesPathEnum.CATEGORY_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCTS;

public class CategoryRedirectCommandImpl implements BaseCommand {
    private static final ProductService productService = new ProductServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
       int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID.getValue()));
        List<Product> products = productService.findByCategoryId(categoryId);
        request.setAttribute(PRODUCTS.getValue(), products);
        return CATEGORY_PAGE.getPath();
    }
}