package by.teachmeskills.controllers;


import by.teachmeskills.services.CategoryService;
import by.teachmeskills.services.ProductService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.enums.PagesPathEnum.SEARCH_PAGE;
import static by.teachmeskills.enums.ShopConstants.CATEGORIES;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView openSearchPage() {
        ModelMap model = new ModelMap();
        model.addAttribute(CATEGORIES, categoryService.read());
        return new ModelAndView(SEARCH_PAGE.getPath(), model);
    }

    @PostMapping
    public ModelAndView search(String searchString) {
        return productService.findProductsByWord(searchString);
    }
}