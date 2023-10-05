package by.teachmeskills.repositories.impl;

import by.teachmeskills.entities.Category;
import by.teachmeskills.repositories.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    private final static Logger log = LogManager.getLogger(CategoryRepositoryImpl.class);
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM shop.categories";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM shop.categories WHERE id=?";
    private static final String DELETE_CATEGORY_BY_ID = "DELETE FROM shop.categories WHERE id=?";
    private static final String ADD_CATEGORY = "INSERT INTO shop.categories (name,imagePath,rating) values (?,?,?)";
    private final static String UPDATE_IMAGE_PATH_AND_RATING_BY_ID = "UPDATE shop.categories SET imagePath = ?, rating = ? WHERE id = ?";


    @Override
    public Category create(Category entity) {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_CATEGORY)) {
            statement.setString(1, entity.getName());
            statement.setString(1, entity.getImagePath());
            statement.setInt(1, entity.getRating());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return entity;
    }

    @Override
    public Category update(Category entity) {
        Connection connection = pool.getConnection();
        int productId = entity.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IMAGE_PATH_AND_RATING_BY_ID);
            preparedStatement.setString(1, entity.getImagePath());
            preparedStatement.setInt(2, entity.getRating());
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
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
    }

    @Override
    public List<Category> read() {
        List<Category> categories = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);
            while (rs.next()) {
                categories.add(Category.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .imagePath(rs.getString(3)).rating(rs.getInt(4)).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public String findNameById(int id) {
        String categoryName = null;
        Connection connection = pool.getConnection();
        try {
            PreparedStatement categoriesStatement = connection.prepareStatement(GET_CATEGORY_BY_ID);
            categoriesStatement.setString(1, String.valueOf(id));
            ResultSet rs = categoriesStatement.executeQuery();
            if (rs.next()) {
                categoryName = rs.getString(1);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            pool.closeConnection(connection);
        }
        return categoryName;
    }
}