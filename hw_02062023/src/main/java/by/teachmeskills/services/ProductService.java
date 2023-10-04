package by.teachmeskills.services;

import by.teachmeskills.entities.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findById(int id);

    List<Product> findByCategoryId(int id);
    List<Product> findProductsByWord(String search);
}