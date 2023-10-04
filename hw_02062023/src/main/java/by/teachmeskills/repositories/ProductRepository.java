package by.teachmeskills.repositories;

import by.teachmeskills.entities.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    Product findById(int id);

    List<Product> findByCategoryId(int id);
    List<Product> findProductsByWord(String search);
}