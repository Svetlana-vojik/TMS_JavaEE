package by.teachmeskills.repositories.impl;

import by.teachmeskills.entities.Product;
import by.teachmeskills.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String ADD_PRODUCT = "INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES(?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "DELETE FROM shop.products WHERE id=?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM shop.products";
    private static final String GET_PRODUCT = "SELECT * FROM shop.products WHERE id=?";
    private static final String GET_CATEGORY_PRODUCTS = "SELECT * FROM shop.products WHERE categoryId=?";
    private final static String UPDATE_DESCRIPTION_AND_PRICE_BY_ID = "UPDATE shop.products SET description = ?, price = ? WHERE id = ?";

    @Override
    public Product create(Product entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPrice());
            statement.setInt(4, entity.getCategoryId());
            statement.setString(5, entity.getImagePath());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public List<Product> read() {
        List<Product> productList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(Product.builder().id(set.getInt("id")).name(set.getString("name"))
                        .description(set.getString("description")).price(set.getInt("price"))
                        .categoryId(set.getInt("categoryId")).imagePath(set.getString("imagePath")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return productList;
    }


    @Override
    public Product update(Product entity) {
        int productId = entity.getId();
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESCRIPTION_AND_PRICE_BY_ID);
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setInt(2, entity.getPrice());
            preparedStatement.setInt(3, productId);
            preparedStatement.execute();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }


    @Override
    public void delete(int id) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRODUCT)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            product = Product.builder().id(set.getInt("id")).name(set.getString("name"))
                    .description(set.getString("description")).price(set.getInt("price"))
                    .categoryId(set.getInt("categoryId")).imagePath(set.getString("imagePath")).build();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int id) {
        List<Product> productList = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_PRODUCTS)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(Product.builder().id(set.getInt("id")).name(set.getString("name"))
                        .description(set.getString("description")).price(set.getInt("price"))
                        .categoryId(set.getInt("categoryId")).imagePath(set.getString("imagePath")).build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return productList;
    }
}