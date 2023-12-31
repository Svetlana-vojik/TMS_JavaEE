package by.teachmeskills.utils;

import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;
import by.teachmeskills.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private CRUDUtils() {
    }

    private final static Logger log = LogManager.getLogger(CRUDUtils.class);
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM shop.categories";
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM shop.products WHERE category_id=?";
    private static final String GET_PRODUCTS_BY_ID = "SELECT * FROM shop.products WHERE id=?";
    private static final String GET_USER = "SELECT * FROM shop.users WHERE email=? and password=?";
    private static final String ADD_USER = "INSERT INTO shop.users (email,password,name,surname,birthday) values (?,?,?,?,?)";


    public static User getUser(String email, String password) {
        User user = null;
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder().email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .surname(resultSet.getString("surname"))
                        .name(resultSet.getString("name"))
                        .birthday(resultSet.getString("birthday"))
                        .build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        return user;
    }

    public static void addUser(User user) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement psInsert = connection.prepareStatement(ADD_USER)) {
            psInsert.setString(1, user.getEmail());
            psInsert.setString(2, user.getPassword());
            psInsert.setString(3, user.getName());
            psInsert.setString(4, user.getSurname());
            psInsert.setString(5, user.getBirthday());
            psInsert.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
    }

    public static List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);
            while (rs.next()) {
                categories.add(Category.builder().id(rs.getString(1)).name(rs.getString(2))
                        .imageName(rs.getString(3)).productList(getProductsByCategoryId(rs.getString(1))).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        return categories;
    }


    public static List<Product> getProductsByCategoryId(String id) {
        List<Product> products = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(Product.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .description(rs.getString(3)).price(rs.getInt(4)).imageName(rs.getString(6)).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            connectionPool.closeConnection(connection);
        }
        return products;
    }

    public static Product getProductById(String productId) {
        Connection connection = connectionPool.getConnection();
        Product product = null;
        try (PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCTS_BY_ID)) {
            productsStatement.setString(1, productId);
            ResultSet productResultSet = productsStatement.executeQuery();
            if (productResultSet.next()) {
                product = Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build();
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection);
        }
    }
}