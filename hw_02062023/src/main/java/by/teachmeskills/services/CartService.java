package by.teachmeskills.services;

import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Product;
import by.teachmeskills.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;


@Service
public class CartService {

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ModelAndView addProductToCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();
        Product product = productRepository.findById(productId);
        cart.addProduct(product);
        modelParams.addAttribute("cart", cart);
        modelParams.addAttribute("product", product);

        return new ModelAndView(PRODUCT_PAGE.getPath(), modelParams);
    }

    public ModelAndView removeProductFromCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();

        cart.removeProduct(productId);
        modelParams.addAttribute("cart", cart);

        return new ModelAndView(CART_PAGE.getPath(), modelParams);
    }
}