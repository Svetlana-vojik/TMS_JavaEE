package by.teachmeskills.utils;

import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;
import by.teachmeskills.model.User;
import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private static final String GET_USER_QUERY = "SELECT * FROM shop.users WHERE email = ? AND password = ?";
    private CRUDUtils() {
    }

    public static User getUserDB(String login, String password, Connection connection) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User(resultSet.getString("email"), resultSet.getString("password"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}