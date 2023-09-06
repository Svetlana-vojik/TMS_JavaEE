package by.teachmeskills.utils;

import by.teachmeskills.model.User;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@UtilityClass
public class CRUDUtils {
    private static final String GET_USER_QUERY = "SELECT * FROM shop.users WHERE email = ? AND password = ?";

    public static User getUserFromDB(String login, String password, Connection connection) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getString("email"), resultSet.getString("password"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}