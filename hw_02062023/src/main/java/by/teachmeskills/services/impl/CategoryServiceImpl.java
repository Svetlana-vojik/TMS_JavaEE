package by.teachmeskills.services.impl;

import by.teachmeskills.enums.ShopConstants;
import by.teachmeskills.entities.Category;
import by.teachmeskills.entities.Product;
import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.repositories.CategoryRepository;
import by.teachmeskills.repositories.ProductRepository;
import by.teachmeskills.services.CategoryService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.enums.ShopConstants.CATEGORY;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }
    @Override
    public ModelAndView create(Category entity) {
        return new ModelAndView();
    }

    @Override
    public List<Category> read() {
        return categoryRepository.read();
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public ModelAndView findNameById(int id) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.CATEGORY_PAGE.getPath());
        Category category = categoryRepository.findNameById(id);
        if (Optional.ofNullable(category).isPresent()) {
            List<Product> products = productService.findByCategoryId(id);
            if (products.size() != 0) {
                modelAndView.addObject(ShopConstants.PRODUCTS, products);
            } else {
                modelAndView.addObject("message", "В данной категории пока нет товаров...");
            }
            modelAndView.addObject(CATEGORY, category.getName());
        }
        return modelAndView;
    }
}