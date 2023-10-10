package by.teachmeskills.services;

import by.teachmeskills.entities.Category;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryService extends BaseService<Category> {
    ModelAndView findNameById(int id);
}