package by.teachmeskills.commands;

import by.teachmeskills.entities.Product;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.exceptions.RequestParamNullException;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import by.teachmeskills.services.impl.CategoryServiceImpl;
import by.teachmeskills.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.teachmeskills.utils.ValidatorUtil.validateParamNotNull;
public class SearchCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);
    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String search = request.getParameter("searchString");
        try {
            validateParamNotNull(search);
            if (search.length() < 3) {
                request.setAttribute("info", "Введите минимум три символа для поиска!");
            } else {
                List<Product> productList = productService.findProductsByWord(search);
                if (productList.size() != 0) {
                    request.setAttribute(RequestParamsEnum.PRODUCTS.getValue(), productList);
                } else {
                    request.setAttribute("message", "Ничего не найдено...");
                }
            }
        } catch (RequestParamNullException e) {
            log.error(e.getMessage());
        }
        request.setAttribute(RequestParamsEnum.CATEGORIES.getValue(), categoryService.read());
        return PagesPathEnum.SEARCH_PAGE.getPath();
    }
}