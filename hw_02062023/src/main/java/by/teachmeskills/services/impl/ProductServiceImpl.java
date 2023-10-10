package by.teachmeskills.services.impl;

import by.teachmeskills.entities.Product;
import by.teachmeskills.repositories.ProductRepository;
import by.teachmeskills.repositories.impl.ProductRepositoryImpl;
import by.teachmeskills.services.ProductService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public Product create(Product entity) {
        return productRepository.create(entity);
    }

    @Override
    public List<Product> read() {
        return productRepository.read();
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public ModelAndView findProductsByWord(String search) {
        return (ModelAndView) productRepository.findProductsByWord(search);
    }
}