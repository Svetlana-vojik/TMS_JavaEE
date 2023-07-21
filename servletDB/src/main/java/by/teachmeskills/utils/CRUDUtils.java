package by.teachmeskills.utils;

import by.teachmeskills.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDUtils {
    private static final String GET_USER_QUERY = "SELECT * FROM user_login WHERE login = ? AND password = ?";

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
                user = new User(resultSet.getString("login"), resultSet.getString("password"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}