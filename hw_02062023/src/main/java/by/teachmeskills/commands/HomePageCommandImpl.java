package by.teachmeskills.commands;

import by.teachmeskills.entities.Category;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class HomePageCommandImpl implements BaseCommand {
    private static final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        List<Category> categoryList = categoryService.read();
        request.setAttribute("categories", categoryList);
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}