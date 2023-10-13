package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.PagesPathEnum;
import by.teachmeskills.shop.repositories.ProductRepository;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.teachmeskills.shop.PagesPathEnum.SEARCH_PAGE;
import static by.teachmeskills.shop.ShopConstants.CATEGORIES;
import static by.teachmeskills.shop.ShopConstants.PRODUCT;
import static by.teachmeskills.shop.ShopConstants.PRODUCTS;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product create(Product entity) {
        return productRepository.create(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.read();
    }

    @Override
    public Product update(Product entity) {
        return productRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public ModelAndView findById(int id) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.PRODUCT_PAGE.getPath());
        modelAndView.addObject(PRODUCT, productRepository.findById(id));
        return modelAndView;
    }

    @Override
    public List<Product> findByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public ModelAndView findProductsByWord(String search) {
        ModelMap modelParam = new ModelMap();
        modelParam.addAttribute(CATEGORIES, categoryService.read());
        if (search.length() < 3) {
            modelParam.addAttribute("info", "Не менее трех символов для поиска!");
        } else {
            List<Product> productList = productRepository.findProductsByWord(search);
            if (productList.size() != 0) {
                modelParam.addAttribute(PRODUCTS, productList);
            } else {
                modelParam.addAttribute("message", "Ничего не найдено...");
            }
        }
        return new ModelAndView(SEARCH_PAGE.getPath(), modelParam);
    }
}