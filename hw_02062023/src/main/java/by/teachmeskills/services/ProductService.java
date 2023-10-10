package by.teachmeskills.services;

import by.teachmeskills.entities.Product;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    ModelAndView findById(int id);

    List<Product> findByCategoryId(int id);
    ModelAndView findProductsByWord(String search);
}