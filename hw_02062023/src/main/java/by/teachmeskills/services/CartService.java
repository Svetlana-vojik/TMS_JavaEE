package by.teachmeskills.services;

import by.teachmeskills.entities.Cart;
import by.teachmeskills.entities.Product;
import by.teachmeskills.repositories.ProductRepository;
import by.teachmeskills.repositories.impl.ProductRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.ShopConstants.CART;
import static by.teachmeskills.enums.ShopConstants.PRODUCT;


@Service
public class CartService {

    private final ProductRepository productRepository = new ProductRepositoryImpl();

    public ModelAndView addProductToCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();
        Product product = productRepository.findById(productId);
        cart.addProduct(product);
        modelParams.addAttribute(CART, cart);
        modelParams.addAttribute(PRODUCT, product);

        return new ModelAndView(PRODUCT_PAGE.getPath(), modelParams);
    }

    public ModelAndView removeProductFromCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();

        cart.removeProduct(productId);
        modelParams.addAttribute(CART, cart);

        return new ModelAndView(CART_PAGE.getPath(), modelParams);
    }
}